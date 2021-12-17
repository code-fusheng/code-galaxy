package xyz.fusheng.bill.common.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @FileName: WebSocketServerConfig
 * @Author: code-fusheng
 * @Date: 2021/12/14 9:51 下午
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@Component
@ServerEndpoint("/push/websocket")
public class WebSocketServer {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    private String sid;

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("[开始监听新的连接:{}, 当前在线人数:{}]", sid, onlineCount);
        this.sid = sid;
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
