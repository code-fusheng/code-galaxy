package xyz.fusheng.admin.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import xyz.fusheng.tool.feign.AuthFeignClientServer;
import xyz.fusheng.tool.feign.UserFeignClientServer;

/**
 * @FileName: UserAdminApplication
 * @Author: code-fusheng
 * @Date: 2021/4/12 2:52 下午
 * @Version: 1.0
 * @Description: 用户后台服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableFeignClients(basePackageClasses = {AuthFeignClientServer.class, UserFeignClientServer.class})
@MapperScan("xyz.fusheng.admin.user.core.mapper")
public class UserAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("用户后台服务[user-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }
}
