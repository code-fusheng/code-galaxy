package xyz.fusheng.bill.common.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.fusheng.bill.model.dto.socket.*;
import xyz.fusheng.core.annotation.OnlineEdit;
import xyz.fusheng.core.exception.BusinessException;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @FileName: MessageHandlerServer
 * @Author: code-fusheng
 * @Date: 2021/12/22 12:28 下午
 * @Version: 1.0
 * @Description: 消息处理
 */

@Slf4j
@Service
public class MessageHandlerServer {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String ROOM_PREFIX_KEY = "ROOM-KEY";

    private static final String ROOM_OBJ_KEY = "roomDto";

    private static final String ONLINE_USER_OBJ_KEY = "onlineUserDto";

    private static final String COLUMN_PREFIX_KEY = "column:";

    @Resource
    private NettyServerHandler nettyServerHandler;

    /**
     * 消息预处理
     * @param ctx
     * @param msg
     */
    public void messagePreHandler(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        MessageDto messageDto = new MessageDto();
        RoomDto roomDto = new RoomDto();
        List<OnlineUserDto> onlineUserDtoList = new LinkedList<>();
        ColumnDto columnDto = new ColumnDto();
        List<ColumnDto> columnDtoList = new ArrayList<>();
        try {
            log.info("[客户端「{}」消息预处理流程-开始] -> msg:{}", ctx.channel().id(), msg.text());
            JSONObject textJSONObject = JSON.parseObject(msg.text());
            String eventType = textJSONObject.getString("eventType");
            log.info("[客户端「{}」消息预处理流程-事件类型解析结果] -> eventType:{}", ctx.channel().id(), eventType);
            switch (eventType) {
                case "INIT_ROOM":
                    roomDto = initRoom(ctx, textJSONObject);
                    messageDto.setEventType("INIT_ROOM");
                    messageDto.setMessageBody(roomDto);
                    nettyServerHandler.sendMessageToAll(null, messageDto);
                    break;
                case "JOIN_ROOM":
                    onlineUserDtoList = joinRoom(ctx, textJSONObject);
                    messageDto.setEventType("JOIN_ROOM");
                    messageDto.setRoomId(textJSONObject.getString("roomId"));
                    messageDto.setMessageBody(onlineUserDtoList);
                    nettyServerHandler.sendMessageToAll(null, messageDto);
                    // 获取当前编辑内容
                    columnDtoList = getColumnDtoList(messageDto.getRoomId());
                    messageDto.setEventType("INIT_COLUMN");
                    messageDto.setMessageBody(columnDtoList);
                    nettyServerHandler.sendMessageToSingle(ctx, messageDto);
                    break;
                case "EXIT_ROOM":
                    onlineUserDtoList = exitRoom(ctx, textJSONObject);
                    messageDto.setEventType("EXIT_ROOM");
                    messageDto.setMessageBody(onlineUserDtoList);
                    nettyServerHandler.sendMessageToAll(null, messageDto);
                    break;
                case "LOCKING_COLUMN":
                    columnDto = lockingColumn(ctx, textJSONObject);
                    messageDto.setEventType("LOCKING_COLUMN");
                    messageDto.setMessageBody(columnDto);
                    nettyServerHandler.sendMessageToAll(ctx, messageDto);
                    break;
                case "RELEASE_COLUMN":
                    columnDto = releaseColumn(ctx, textJSONObject);
                    messageDto.setEventType("RELEASE_COLUMN");
                    messageDto.setMessageBody(columnDto);
                    nettyServerHandler.sendMessageToAll(ctx, messageDto);
                    break;
                default:
                    throw new BusinessException("eventType事件类型不匹配!");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 初始化房间信息
     * @param ctx
     * @param textJSONObject
     */
    private RoomDto initRoom(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        RoomDto roomDto;
        log.info("[客户端「{}」初始化房间信息-开始] -> msg:{}", ctx.channel().id(), textJSONObject);
        String roomType = textJSONObject.getString("roomType");
        log.info("[客户端「{}」初始化房间信息-房间类型解析结果] -> roomType:{}", ctx.channel().id(), roomType);
        switch (roomType) {
            case "COMPANY_REG":
                roomDto = intiCompanyRegRoom(ctx, textJSONObject);
                return roomDto;
            default:
                return null;
        }
    }

    private RoomDto intiCompanyRegRoom(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        // 初始化房间信息
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(textJSONObject.getString("roomId"));
        roomDto.setRoomType(textJSONObject.getString("roomType"));
        roomDto.setUserId(ctx.channel().id().toString());
        redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + roomDto.getRoomId() , ROOM_OBJ_KEY, roomDto);
        log.info("[客户端「{}」初始化房间信息-Redis同步成功] -> roomDto:{}", ctx.channel().id(), JSON.toJSON(roomDto));
        // 初始化在线用户列表
        OnlineUserDto onlineUserDto = new OnlineUserDto();
        onlineUserDto.setCid(ctx.channel().id().toString());
        onlineUserDto.setUserId(textJSONObject.getString("userId"));
        onlineUserDto.setUserRole("creator");
        LinkedList<OnlineUserDto> onlineUserList = new LinkedList<>();
        onlineUserList.add(onlineUserDto);
        redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + roomDto.getRoomId(), ONLINE_USER_OBJ_KEY, onlineUserList);
        log.info("[客户端「{}」初始化在线用户信息-Redis同步成功] -> roomDto:{}", ctx.channel().id(), JSON.toJSON(roomDto));
        // 初始化企业注册案件字段信息
        List<ColumnDto> columnDtoList = initColumnDtoList(roomDto.getRoomId());
        log.info("[客户端「{}」初始化字段编辑信息-Redis同步成功] -> columnDtoList:{}", ctx.channel().id(), columnDtoList);
        return roomDto;
    }

    /**
     * 加入房间
     * @param ctx
     * @param textJSONObject
     */
    private List<OnlineUserDto> joinRoom(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        log.info("[客户端「{}」加入房间流程-开始] -> msg:{}", ctx.channel().id(), textJSONObject);
        String roomType = textJSONObject.getString("roomType");
        log.info("[客户端「{}」加入房间流程-房间类型解析结果] -> roomType:{}", ctx.channel().id(), roomType);
        switch (roomType) {
            case "COMPANY_REG":
                List<OnlineUserDto> onlineUserList = joinCompanyRegRoom(ctx, textJSONObject);
                return onlineUserList;
            default:
                return null;
        }
    }

    /**
     * 退出房间
     * @param ctx
     * @param textJSONObject
     */
    private List<OnlineUserDto> exitRoom(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        log.info("[客户端「{}」退出房间流程-开始] -> msg:{}", ctx.channel().id(), textJSONObject);
        String roomType = textJSONObject.getString("roomType");
        log.info("[客户端「{}」退出房间流程-房间类型解析结果] -> roomType:{}", ctx.channel().id(), roomType);
        switch (roomType) {
            case "COMPANY_REG":
                List<OnlineUserDto> onlineUserDtoList = exitCompanyRegRoom(ctx, textJSONObject);
                return onlineUserDtoList;
            default:
                return Collections.emptyList();
        }
    }

    private List<OnlineUserDto> exitCompanyRegRoom(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        RoomDto roomDto = getRoomInfo(textJSONObject.getString("roomId"));
        List<OnlineUserDto> onlineUserListForRedis = getOnlineUserInfoForRedis(textJSONObject.getString("roomId"));
        List<OnlineUserDto> nowOnlineUserList = onlineUserListForRedis.stream().filter(x -> !x.getCid().equals(String.valueOf(ctx.channel().id()))).collect(Collectors.toList());
        redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + roomDto.getRoomId(), ONLINE_USER_OBJ_KEY, JSON.toJSON(nowOnlineUserList));
        log.info("[客户端「{}」退出在线用户信息列表-Redis同步成功] -> nowOnlineUserList:{}", ctx.channel().id(), nowOnlineUserList);
        return nowOnlineUserList;
    }

    private List<OnlineUserDto> joinCompanyRegRoom(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        RoomDto roomDto = getRoomInfo(textJSONObject.getString("roomId"));
        OnlineUserDto onlineUserDto = new OnlineUserDto();
        onlineUserDto.setCid(ctx.channel().id().toString());
        onlineUserDto.setUserId(textJSONObject.getString("userId"));
        onlineUserDto.setUserRole("editor");
        // 检查房间是否存在
        // ==> 存在 -> 加入房间-跳过房间初始化流程
        // ==> 不存在 -> 创建房间 & 直接异常
        List<OnlineUserDto> onlineUserList = getOnlineUserInfoForRedis(textJSONObject.getString("roomId"));
        onlineUserList.add(onlineUserDto);
        redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + roomDto.getRoomId(), ONLINE_USER_OBJ_KEY, JSON.toJSON(onlineUserList));
        log.info("[客户端「{}」加入在线用户信息列表-Redis同步成功] -> onlineUserList:{}", ctx.channel().id(), onlineUserList);
        return onlineUserList;
    }

    private RoomDto getRoomInfo(String roomId) {
        Object o = redisTemplate.opsForHash().get(ROOM_PREFIX_KEY + ":" + roomId, ROOM_OBJ_KEY);
        RoomDto roomDto = JSON.parseObject(JSON.toJSONString(o), RoomDto.class);
        log.info("房间信息:{}", roomDto);
        return roomDto;
    }

    private List<OnlineUserDto> getOnlineUserInfoForRedis(String roomId) {
        Object o = redisTemplate.opsForHash().get(ROOM_PREFIX_KEY + ":" + roomId, ONLINE_USER_OBJ_KEY);
        List<OnlineUserDto> onlineUserDtoList = JSON.parseArray(JSON.toJSONString(o), OnlineUserDto.class);
        log.info("房间在线用户:{}", onlineUserDtoList);
        return onlineUserDtoList;
    }

    private ColumnDto lockingColumn(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        MessageDto messageDto = JSON.toJavaObject(textJSONObject, MessageDto.class);
        ColumnDto columnDto = JSON.toJavaObject((JSON) JSON.toJSON(messageDto.getMessageBody()), ColumnDto.class);
        redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + messageDto.getRoomId(), COLUMN_PREFIX_KEY + columnDto.getColumnKey(), JSON.toJSON(columnDto));
        log.info("[客户端「{}」锁定字段] -> columnDto:{}", ctx.channel().id(), columnDto);
        return columnDto;
    }

    private ColumnDto releaseColumn(ChannelHandlerContext ctx, JSONObject textJSONObject) {
        MessageDto messageDto = JSON.toJavaObject(textJSONObject, MessageDto.class);
        ColumnDto columnDto = JSON.toJavaObject((JSON) JSON.toJSON(messageDto.getMessageBody()), ColumnDto.class);
        redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + messageDto.getRoomId(), COLUMN_PREFIX_KEY + columnDto.getColumnKey(), JSON.toJSON(columnDto));
        log.info("[客户端「{}」释放字段] -> columnDto:{}", ctx.channel().id(), columnDto);
        return columnDto;
    }

    private List<ColumnDto> initColumnDtoList(String roomId) {
        List<ColumnDto> columnDtoList = new ArrayList<>();
        RoomDto roomInfo = getRoomInfo(roomId);
        switch (roomInfo.getRoomType()) {
            case "COMPANY_REG":
                for (Field field : CompanyRegMsgDto.class.getDeclaredFields()) {
                    OnlineEdit annotation = field.getAnnotation(OnlineEdit.class);
                    if (annotation == null) {
                        continue;
                    }
                    ColumnDto columnDto = new ColumnDto(annotation.columnKey());
                    redisTemplate.opsForHash().put(ROOM_PREFIX_KEY + ":" + roomId, COLUMN_PREFIX_KEY + annotation.columnKey(), columnDto);
                    columnDtoList.add(columnDto);
                }
                return columnDtoList;
            default:
                return null;
        }
    }

    private List<ColumnDto> getColumnDtoList(String roomId) {
        RoomDto roomInfo = getRoomInfo(roomId);
        List<ColumnDto> columnDtoList = new ArrayList<>();
        switch (roomInfo.getRoomType()) {
            case "COMPANY_REG":
                for (Field field : CompanyRegMsgDto.class.getDeclaredFields()) {
                    OnlineEdit annotation = field.getAnnotation(OnlineEdit.class);
                    if (annotation == null) {
                        continue;
                    }
                    Object o = redisTemplate.opsForHash().get(ROOM_PREFIX_KEY + ":" + roomId, COLUMN_PREFIX_KEY + annotation.columnKey());
                    ColumnDto columnDto = JSON.toJavaObject((JSON) JSON.toJSON(o) , ColumnDto.class);
                    if (Objects.isNull(columnDto)) {
                        columnDto = new ColumnDto(annotation.columnKey());
                    }
                    columnDtoList.add(columnDto);
                }
                return columnDtoList;
            default:
                return null;
        }
    }
}
