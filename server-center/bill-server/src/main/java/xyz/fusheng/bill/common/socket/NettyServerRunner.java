package xyz.fusheng.bill.common.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @FileName: NettyServerRunner
 * @Author: code-fusheng
 * @Date: 2021/12/19 8:45 下午
 * @Version: 1.0
 * @Description: NettyServer自启动类
 */

@Slf4j
@Component
public class NettyServerRunner implements CommandLineRunner {

    @Autowired
    private NettyServer server;

    @Override
    public void run(String... args) throws Exception {
        server.start();
    }

}
