package xyz.fusheng.bill.common.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @FileName: NettyServer
 * @Author: code-fusheng
 * @Date: 2021/12/19 3:42 下午
 * @Version: 1.0
 * @Description: Netty服务启动类
 */

@Slf4j
@Component
public class NettyServer {

    @Value("${server.netty.port:12345}")
    private int port;

    @Autowired
    private NettyServerChannelInitializer nettyServerChannelInitializer;

    public void start() throws Exception {
        // 主线程组 - 用于处理网络连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 工作线程组 - 用于处理消息读写
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // 服务引导
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(nettyServerChannelInitializer)
                // 设置队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 两个小时内没有数据的通信，TCP会自动发送一个活动探测的数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        // 绑定端口，开始接收进来的连接
        try {
            // 绑定端口 同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("[Netty服务启动-开始监控端口] -> Port:{}", port);
            // 等待服务端监控端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            // 中断异常
            log.info("[Netty服务启动-异常] -> 异常信息:{}", e.getMessage(), e);
        } finally {
            // 释放主线程组
            bossGroup.shutdownGracefully();
            // 释放工作线程组
            workGroup.shutdownGracefully();
        }
    }
}
