package xyz.fusheng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import xyz.fusheng.service.SelfUserDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @FileName: AuthorizationServerConfig
 * @Author: code-fusheng
 * @Date: 2021/4/7 5:53 下午
 * @Version: 1.0
 * @Description: 授权/认证服务器配置
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SelfUserDetailsService userDetailsService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    /**
     * access_token 存储器
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 数据库读取 clientDetails
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证服务器接口权限管理
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated");
    }

    /**
     * client 存储方式
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetails());
        clients.inMemory()
                .withClient("test-server")     // client_id
                .secret(new BCryptPasswordEncoder().encode("test"))   // 客户端密钥
                .resourceIds("test-server")   // 资源列表
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("web")  // 允许的授权范围
                .autoApprove(true)     // 跳转授权页面
                .redirectUris("http://www.baidu.com")
                .and()
                .withClient("user-server")
                .secret(new BCryptPasswordEncoder().encode("user"))
                .resourceIds("user-server")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("web")
                .autoApprove(true)
                .redirectUris("http://www.baidu.com")
                .and()
                .withClient("article-server")
                .secret(new BCryptPasswordEncoder().encode("article"))
                .resourceIds("article-server")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("web")  // 允许的授权范围
                .autoApprove(true)     // 跳转授权页面
                .redirectUris("http://www.baidu.com")
                .and()
                .withClient("exam-server")
                .secret(new BCryptPasswordEncoder().encode("exam"))
                .resourceIds("exam-server")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("web")
                .autoApprove(true)
                .redirectUris("http://www.baidu.com");
    }

    /**
     * 认证服务器 Enpoints配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailsService);
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
    }
}
