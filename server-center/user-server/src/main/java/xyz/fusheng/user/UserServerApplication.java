package xyz.fusheng.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import xyz.fusheng.core.feign.AuthFeignClientServer;
import xyz.fusheng.core.feign.SysFeignClientServer;
import xyz.fusheng.core.feign.UserFeignClientServer;

/**
 * @FileName: UserServerApplication
 * @Author: code-fusheng
 * @Date: 2021/4/7 12:24 下午
 * @Version: 1.0
 * @Description: 用户服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {AuthFeignClientServer.class, UserFeignClientServer.class, SysFeignClientServer.class})
@MapperScan("xyz.fusheng.user.core.mapper")
@EnableCaching//开启注解驱动的缓存管理
public class UserServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("用户服务[user-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
