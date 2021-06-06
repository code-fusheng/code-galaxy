package xyz.fusheng.gateway.common.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * @FileName: WebFluxWithOpenFeignConfig
 * @Author: code-fusheng
 * @Date: 2021/4/14 12:29 下午
 * @Version: 1.0
 * @Description: 解决 GateWay 调用 Feign 问题
 */

@Configuration
public class WebFluxWithOpenFeignConfig {
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
