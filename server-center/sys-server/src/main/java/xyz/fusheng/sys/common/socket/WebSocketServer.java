package xyz.fusheng.sys.common.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import xyz.fusheng.sys.common.redis.RedisUtils;
import xyz.fusheng.sys.common.socket.entity.RoomObj;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @FileName: WebSocketServer
 * @Author: code-fusheng
 * @Date: 2021/9/1 11:48 上午
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@Component
@ServerEndpoint("/push/websocket")
public class WebSocketServer {

    private static MultiplayerEditServer multiplayerEditServer;

    @Autowired
    public void setMultiplayerEditServer(MultiplayerEditServer multiplayerEditServer) {
        WebSocketServer.multiplayerEditServer = multiplayerEditServer;
    }

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    private String sid;

    /**
     * 建立websocket连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        log.info("[sid:{}]", session.getId());
        // String key1 = session.getPathParameters().get("key");
        System.out.println(session.getRequestURI().getPath());
        Map<String, List<String>> paramsMap = session.getRequestParameterMap();
        log.info("{}", paramsMap);
        String caseNo = paramsMap.get("caseNo").get(0);
        String userId = paramsMap.get("userId").get(0);
        webSocketSet.add(this);
        addOnlineCount();
        log.info("[新的连接:{}, 当前在线人数:{}, 房间号:{}]", session.getId(), onlineCount, caseNo);
        // 开始处理 redis 缓存信息
        RoomObj roomObj = new RoomObj();
        roomObj.setCaseNo(caseNo);
        roomObj.setTargetId(caseNo);
        // redisTemplate.opsForValue().set(roomObj.getRoomNo() , roomObj);
        multiplayerEditServer.joinEditRoom(session, roomObj);
    }

    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("[断开连接:{}, 当前在线人数:{}]", session.getId(), onlineCount);
        multiplayerEditServer.exitEditRoom(session);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
                log.info("[推送消息到连接的客户端:{}, 推送内容:{}]", item.sid, message);
            } catch (Exception e) {
                continue;
            }
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线人数 + 1
     */
    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount ++;
    }

    /**
     * 在线人数 - 1
     */
    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount --;
    }

}
