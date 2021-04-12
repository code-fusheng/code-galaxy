package xyz.fusheng;

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
 * @FileName: ExamServerApplication
 * @Author: code-fusheng
 * @Date: 2021/4/10 11:19 上午
 * @Version: 1.0
 * @Description: 考试服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("xyz.fusheng.mapper")
@EnableResourceServer
public class ExamServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ExamServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ExamServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("考试服务[exam-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
