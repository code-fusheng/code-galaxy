package xyz.fusheng.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import xyz.fusheng.feign.UserFeignClientServer;

/**
 * @FileName: AuthServerApplication
 * @Author: code-fusheng
 * @Date: 2021/4/7 5:34 下午
 * @Version: 1.0
 * @Description: 认证服务启动类
 * 1、EnableResourceServer 对外开放获取 token 的 API 接口
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = UserFeignClientServer.class)
public class AuthServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AuthServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("认证服务[auth-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
