package xyz.fusheng.admin.sys;

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
import xyz.fusheng.core.feign.AuthFeignClientServer;
import xyz.fusheng.core.feign.SysFeignClientServer;
import xyz.fusheng.core.feign.UserFeignClientServer;

/**
 * @FileName: BaseAdminApplication
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:09 下午
 * @Version: 1.0
 * @Description: 基础服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableFeignClients(basePackageClasses = {SysFeignClientServer.class})
@MapperScan("xyz.fusheng.admin.sys.core.mapper")
public class SysAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(SysAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SysAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("系统后台服务[sys-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
