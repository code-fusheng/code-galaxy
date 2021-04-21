package xyz.fusheng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @FileName: GatewayServerApplication
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:12 上午
 * @Version: 1.0
 * @Description: 网关服务启动类
 */


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GatewayServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("网关服务[gateway-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
