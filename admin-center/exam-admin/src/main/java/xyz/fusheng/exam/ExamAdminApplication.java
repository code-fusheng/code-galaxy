package xyz.fusheng.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @FileName: ExamAdminApplication
 * @Author: code-fusheng
 * @Date: 2021/4/22 5:32 下午
 * @Version: 1.0
 * @Description: 考试管理服务启动类
 */

@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@MapperScan("xyz.fusheng.exam.core.mapper")
public class ExamAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(ExamAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ExamAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("考试后台服务[exam-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
