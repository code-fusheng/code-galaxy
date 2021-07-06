package xyz.fusheng.admin.bill;

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
 * @FileName: BillAdminApplication
 * @Author: code-fusheng
 * @Date: 2021/7/4 上午10:10
 * @Version: 1.0
 * @Description: 记账管理启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@MapperScan("xyz.fusheng.admin.bill.core.mapper")
public class BillAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(BillAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BillAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("记账后台服务[bill-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }


}
