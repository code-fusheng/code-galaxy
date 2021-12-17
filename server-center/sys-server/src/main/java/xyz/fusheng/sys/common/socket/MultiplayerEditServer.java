package xyz.fusheng.sys.common.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.fusheng.sys.common.socket.entity.EditObj;
import xyz.fusheng.sys.common.socket.entity.OnlineUserObj;
import xyz.fusheng.sys.common.socket.entity.RoomObj;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @FileName: MultiplayerEditServer
 * @Author: code-fusheng
 * @Date: 2021/12/15 3:32 下午
 * @Version: 1.0
 * @Description: 协同业务逻辑
 */

@Slf4j
@Service
public class MultiplayerEditServer {

    private static final int TTL_MIN_30 = 1800;

    private static final String ROOM_KEY_PREFIX = "editRoom:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RoomObj initEditRoom(Session session, RoomObj roomObj) {
        String sid = session.getId();
        Map<String, List<String>> paramsMap = session.getRequestParameterMap();
        String userId = paramsMap.get("userId").get(0);
        OnlineUserObj onlineUserObj = new OnlineUserObj();
        onlineUserObj.setUserId(userId);
        LinkedList<OnlineUserObj> userList = new LinkedList<>();
        userList.add(onlineUserObj);
        roomObj.setUserList(userList);
        roomObj.setEditObj(new EditObj() {{ setColumn1("1"); }});
        log.info("[Redis数据维护:创建房间] -> 房间信息:{}", roomObj);
        redisTemplate.opsForValue().set(ROOM_KEY_PREFIX + roomObj.getCaseNo(), JSONObject.toJSON(roomObj));
        return roomObj;
    }

    public void joinEditRoom(Session session, RoomObj roomObj) {
        // 检查房间是否存在
        Map<String, List<String>> paramsMap = session.getRequestParameterMap();
        String caseNo = paramsMap.get("caseNo").get(0);
        String userId = paramsMap.get("userId").get(0);
        Object value = redisTemplate.opsForValue().get(ROOM_KEY_PREFIX + roomObj.getCaseNo());
        log.info("[{}]", value);
        if (Objects.isNull(value)) {
            // 房间不存在 - 创建房间
            roomObj = this.initEditRoom(session, roomObj);
        } else {
            String sid = session.getId();
            OnlineUserObj onlineUserObj = new OnlineUserObj();
            onlineUserObj.setUserId(userId);
            RoomObj oldRoomObj = JSONObject.parseObject(String.valueOf(value), RoomObj.class);
            List<OnlineUserObj> userList = oldRoomObj.getUserList();
            if (CollectionUtils.isEmpty(userList)) {
                userList = new LinkedList<>();
                userList.add(onlineUserObj);
            } else {
                userList.add(onlineUserObj);
            }
            oldRoomObj.setUserList(userList);
            log.info("[Redis数据维护:加入房间] -> 房间信息:{}", oldRoomObj);
            redisTemplate.opsForValue().set(ROOM_KEY_PREFIX + oldRoomObj.getCaseNo(), JSONObject.toJSON(oldRoomObj));
            roomObj = oldRoomObj;
        }
        try {
            log.info("发送消息");
            WebSocketServer.sendInfo(JSON.toJSONString(roomObj));
        } catch (Exception e) {
            log.info("[{}]", e.getMessage(), e);
        }
    }


    public void exitEditRoom(Session session) {
        Map<String, List<String>> paramsMap = session.getRequestParameterMap();
        String caseNo = paramsMap.get("caseNo").get(0);
        String userId = paramsMap.get("userId").get(0);
        Object value = redisTemplate.opsForValue().get(ROOM_KEY_PREFIX + caseNo);
        log.info("[{}]", value);
        RoomObj roomObj = JSONObject.parseObject(String.valueOf(value), RoomObj.class);
        List<OnlineUserObj> onlineUserList = roomObj.getUserList().stream().filter(item -> !userId.equals(item.getUserId())).collect(Collectors.toList());
        roomObj.setUserList(onlineUserList);
        log.info("[Redis数据维护:退出房间] -> 房间信息:{}", roomObj);
        redisTemplate.opsForValue().set(ROOM_KEY_PREFIX + roomObj.getCaseNo(), JSONObject.toJSON(roomObj));
        try {
            log.info("发送消息");
            WebSocketServer.sendInfo(JSON.toJSONString(roomObj));
        } catch (Exception e) {
            log.info("[{}]", e.getMessage(), e);
        }
    }
}
