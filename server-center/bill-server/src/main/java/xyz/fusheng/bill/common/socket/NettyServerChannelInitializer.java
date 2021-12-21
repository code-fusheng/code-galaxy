package xyz.fusheng.bill.common.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

/**
 * @FileName: NettyServerChannelInitializar
 * @Author: code-fusheng
 * @Date: 2021/12/19 4:14 下午
 * @Version: 1.0
 * @Description: Netty服务初始化
 */

@Component
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 沾包&拆包 - 读半包问题 处理 非必须的解码器
        // PS: 拓展解码器
        // DelimiterBasedFrameDecoder 指定分隔符结尾
        // FixedLengthFrameDecoder 固定长度
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        pipeline.addLast(new StringDecoder());
        // 以下三个是http的支持
        // http 解码器
        pipeline.addLast(new HttpServerCodec());
        // 支持写大数据流
        pipeline.addLast(new ChunkedWriteHandler());
        // http 聚合器
        pipeline.addLast(new HttpObjectAggregator(1024*62));
        // websocket 设置路由
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 添加自定义的处理类
        pipeline.addLast(new NettyServerHandler());
    }
}
