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
 * @FileName: TestApplication
 * @Author: code-fusheng
 * @Date: 2021/4/7 10:13 上午
 * @Version: 1.0
 * @Description: 测试服务启动类
 *
 * 1、@EnableResourceServer 注解 会为 Spring Security 的  FilterChan 添加一个 OAuth2AuthenticationProcessingFilter
 *    会使用 OAuth2AuthenticationManager 来验证 Token
 *    断点调试: OAuth2AuthenticationProcessingFilter#doFilter()
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("xyz.fusheng.mapper")
@EnableResourceServer
public class TestServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(TestServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("测试服务[test-server]已经启动,端口号:{}", environment.getProperty("server.port"));
        String username = applicationContext.getEnvironment().getProperty("user.username");
        String password = applicationContext.getEnvironment().getProperty("user.password");
        logger.info("username:{}, password:{}", username, password);
    }

}
