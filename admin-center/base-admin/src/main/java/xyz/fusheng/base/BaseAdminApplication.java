package xyz.fusheng.base;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @FileName: BaseAdminApplication
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:09 下午
 * @Version: 1.0
 * @Description: 基础服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("xyz.fusheng.base.mapper")
public class BaseAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(BaseAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BaseAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("基础后台服务[base-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
