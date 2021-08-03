package xyz.fusheng.mall;

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

/**
 * @FileName: MallServerApplication
 * @Author: code-fusheng
 * @Date: 2021/7/13 下午2:07
 * @Version: 1.0
 * @Description:
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("xyz.fusheng.mall.core.mapper")
@EnableResourceServer
@EnableFeignClients
public class MallServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(MallServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MallServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("商场服务[mall-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
