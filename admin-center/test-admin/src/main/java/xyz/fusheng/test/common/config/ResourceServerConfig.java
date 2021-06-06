package xyz.fusheng.test.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @FileName: ResourceServerConfig
 * @Author: code-fusheng
 * @Date: 2021/4/8 11:50 上午
 * @Version: 1.0
 * @Description: 配置资源服务器
 */

@Configuration
@EnableResourceServer   // 标识为资源服务器，请求服务中的资源务必携带token过来,找不到token或是无效访问不了资源
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 开启方法级别权限控制
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter jwtTokenEnhancer = new JwtAccessTokenConverter();
        jwtTokenEnhancer.setSigningKey("fusheng");
        return jwtTokenEnhancer;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/v2/api-docs/**", "/doc.html", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**"
                ).permitAll()
                // 放行所有 /api 开头的请求
                .antMatchers("/api/**").permitAll()
                // 所有请求都需要有 all 范围
                .antMatchers("/api/**").access("#oauth2.hasScope('all')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                resources.tokenStore(tokenStore());
    }
}
