package xyz.fusheng.admin.article;

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
 * @FileName: ArticleApplicaton
 * @Author: code-fusheng
 * @Date: 2021/5/17 2:22 上午
 * @Version: 1.0
 * @Description:
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@MapperScan("xyz.fusheng.admin.article.core.mapper")
public class ArticleAdminApplication {

    private static final Logger logger = LoggerFactory.getLogger(ArticleAdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ArticleAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("文章后台服务[article-admin-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
