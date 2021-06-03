package xyz.fusheng.auth.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import xyz.fusheng.auth.service.SelfUserDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: AuthorizationServerConfig
 * @Author: code-fusheng
 * @Date: 2021/6/3 2:35 下午
 * @Version: 1.0
 * @Description: 认证服务器配置
 */

@Configuration
@EnableAuthorizationServer  // 开启认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource   // 数据源
    private DataSource dataSource;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Bean       // 客户端jdbc管理
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 1、配置被允许访问此认证服务器的客户端信息:数据库方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 客户端jdbc管理
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 2、在 SecurityConfig 中添加到了容器 @Bean
     */
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SelfUserDetailsService selfUserDetailsService;

    @Resource   // token 管理方式,引用 JwtAccessTokenConverter
    private TokenStore tokenStore;

    @Resource   // jwt 转换器
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 认证服务器端点配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 密码认证需要
        endpoints.authenticationManager(authenticationManager);
        // 刷新令牌需要
        endpoints.userDetailsService(selfUserDetailsService);
        // 令牌的管理方式
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);
        // JWT 内容拓展器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
        endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 解析令牌，默认情况下禁止访问
        security.checkTokenAccess("permitAll()");
    }
}
