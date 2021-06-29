package xyz.fusheng.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @FileName: ArticleApplication
 * @Author: code-fusheng
 * @Date: 2021/6/15 3:39 下午
 * @Version: 1.0
 * @Description: 文章服务启动类
 */
@SpringBootApplication
public class ArticleServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServerApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ArticleServerApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("文章服务[article-server]已经启动,端口号:{}", environment.getProperty("server.port"));
    }

}
