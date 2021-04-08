package xyz.fusheng.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @FileName: ResourceServerConfig
 * @Author: code-fusheng
 * @Date: 2021/4/8 11:50 上午
 * @Version: 1.0
 * @Description: 配置资源服务器
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers(
                        "/v2/api-docs/**", "/doc.html", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .csrf().disable();
    }
}
