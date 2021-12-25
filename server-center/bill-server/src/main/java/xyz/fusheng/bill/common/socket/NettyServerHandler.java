package xyz.fusheng.bill.common.socket;

import com.alibaba.fastjson.JSON;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: NettyServerHandler
 * @Author: code-fusheng
 * @Date: 2021/12/19 4:29 下午
 * @Version: 1.0
 * @Description: Netty服务端处理器
 * TextWebSocketFrame netty 用于处理 websocket 的文本对象
 */

@Slf4j
@Component
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static MessageHandlerServer messageHandlerServer;

    @Autowired
    public void setMessageHandlerServer(MessageHandlerServer messageHandlerServer) {
        NettyServerHandler.messageHandlerServer = messageHandlerServer;
    }

    //可以存储userId与ChannelId的映射表
    public static ConcurrentHashMap<String, ChannelId> channelIdMap = new ConcurrentHashMap<>();

    //channelGroup通道组
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 客户端建立连接
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        log.info("[建立连接] -> cid:{}", ctx.channel().id());
    }

    // 客户端断开连接
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
        log.info("[断开连接] -> cid:{}", ctx.channel().id());
    }

    // 出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channelGroup.remove(ctx.channel());
        log.info("[连接异常] -> cause:{}", cause.getMessage());
    }

    // 接收到客户端发送的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("[接收消息] -> 客户端:{}, 数据:{}", ctx.channel().id(), msg.text());
        messageHandlerServer.messagePreHandler(ctx, msg);
    }

    // 发送消息至单一对象
    public void sendMessageToSingle(ChannelHandlerContext ctx, Object obj) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(obj)));
        log.info("[单点消息] -> 客户端:{}, 数据:{}", ctx.channel().id(), obj);
    }

    // 发送消息给所有对象
    public void sendMessageToAll(ChannelHandlerContext ctx, Object obj) {
        for (Channel channel : channelGroup) {
            if (Objects.nonNull(ctx)) {
                if (!channel.id().asLongText().equals(ctx.channel().id().asLongText())) {
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(obj)));
                    log.info("[广播消息] -> 客户端:{}, 数据:{}", channel.id(), obj);
                }
            } else {
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(obj)));
                log.info("[广播消息] -> 客户端:{}, 数据:{}", channel.id(), obj);
            }
        }
    }

}
