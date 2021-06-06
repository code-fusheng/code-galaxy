package xyz.fusheng.admin.test.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @FileName: Swagger2Config
 * @Author: code-fusheng
 * @Date: 2021/4/7 2:31 下午
 * @Version: 1.0
 * @Description: Swagger2配置类
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {

    /**
     * 配置一个 Docket Bean
     * 配置映射路径与需要扫描的接口位置
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(apiInfo("code-galaxy 接口文档", "测试服务(后台)接口文档"));
    }


    /**
     * 主要配置接口网站的信息
     * http://localhost:10010/doc.html
     * @return
     */
    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("2.0")
                .build();
    }

}
