package xyz.fusheng.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @FileName: AuthorizationServerConfig
 * @Author: code-fusheng
 * @Date: 2021/4/7 5:53 下午
 * @Version: 1.0
 * @Description: 授权/认证服务器配置
 *
 * OAuth2.0 认证授权过程解析
 * 1、用户通过 endpoint.TokenEndpoint#postAccessToken(java.security.Principal, java.util.Map) 方法请求获取 Access_Token
 * 2、根据用户 client_id 通过 ClientDetailsService#loadClientByClientId(java.lang.String) 方法获取客户端信息
 *    其中包括 InMemoryClientDetailsService 从内存获取客户端信息 / JdbcClientDetailsService 从数据库获取客户端信息
 * 3、通过 DefaultOAuth2RequestFactory#createTokenRequest() 方法从用户请求中提取信息 [客户端Id、认证类型、作用范围等等]
 * 4、生成 Token 流程 ->  token.AbstractTokenGranter#grant() 其中包括校验类型处理 #validateGrantType 、Token 组装 #createAccessToken
 * 5、org.springframework.security.authentication.ProviderManager 提供了认证的实现逻辑和流程、负责从所有的认证提供者中找出具体的 provider 进行认证
 *
 * PS：用户名密码模式与授权码模式差异
 *     用户名模式通过直接调用 /oauth/token 获取 access_token 适用于系统自身存在用户信息(需要用户名和密码)
 *     验证码模式通过访问 /oauth/authorize 端点获取 code 授权码, 然后调用 /oauth/token 获取 access_token(需要code，不需要用户名密码)
 */

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenGranter tokenGranter;

    @Autowired
    private DataSource dataSource;

    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * access_token 存储器
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    /**
     * TokenEnhancer 的子类，帮助程序在 JWT 编码的令牌值和 Oauth身份验证信息之间转换
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 设置对称签名
        converter.setSigningKey("fusheng");
        return converter;
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService());
        tokenServices.setTokenEnhancer(jwtTokenEnhancer());
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // 6 小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 6);
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
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
        clients.withClientDetails(clientDetailsService());
//        clients.inMemory()
//                .withClient("test-server")     // client_id
//                .secret(new BCryptPasswordEncoder().encode("test"))   // 客户端密钥
//                .resourceIds("test-server")   // 资源列表
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token", "sms_code")
//                .scopes("web")  // 允许的授权范围
//                .autoApprove(false)     // 跳转授权页面
//                .redirectUris("http://localhost:9999/doc.html")
//                .and()
//                .withClient("user-server")
//                .secret(new BCryptPasswordEncoder().encode("user"))
//                .resourceIds("user-server")
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token", "sms_code")
//                .scopes("web")
//                .autoApprove(true)
//                .redirectUris("http://www.baidu.com")
//                .and()
//                .withClient("article-server")
//                .secret(new BCryptPasswordEncoder().encode("article"))
//                .resourceIds("article-server")
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token", "sms_code")
//                .scopes("web")  // 允许的授权范围
//                .autoApprove(true)     // 跳转授权页面
//                .redirectUris("http://www.baidu.com")
//                .and()
//                .withClient("exam-server")
//                .secret(new BCryptPasswordEncoder().encode("exam"))
//                .resourceIds("exam-server")
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token", "sms_code")
//                .scopes("web")
//                .autoApprove(true)
//                .redirectUris("http://www.baidu.com");
    }

    /**
     * 认证服务器 Enpoints配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 注入 jwtTokenEnhancer 转换器
        endpoints.tokenGranter(tokenGranter);
    }
}
