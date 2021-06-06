package xyz.fusheng.admin.test;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @FileName: TestAdminApplication
 * @Author: code-fusheng
 * @Date: 2021/6/6 12:49 上午
 * @Version: 1.0
 * @Description: 测试管理启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@MapperScan("xyz.fusheng.admin.test.core.mapper")
public class TestAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(TestAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("测试后台服务[test-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
