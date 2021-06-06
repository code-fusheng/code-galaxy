package xyz.fusheng.auth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import xyz.fusheng.auth.common.SelfLoginFailureHandler;
import xyz.fusheng.auth.common.SelfLoginSuccessHandler;
import xyz.fusheng.auth.common.SelfLogoutSuccessHandler;
import xyz.fusheng.auth.core.service.SelfUserDetailsService;

import javax.annotation.Resource;

/**
 * @FileName: SecurityConfig
 * @Author: code-fusheng
 * @Date: 2021/6/3 1:55 下午
 * @Version: 1.0
 * @Description: 安全配置类
 *
 *  * OAuth2.0 认证授权过程解析
 *  * 1、用户通过 endpoint.TokenEndpoint#postAccessToken(java.security.Principal, java.util.Map) 方法请求获取 Access_Token
 *  * 2、根据用户 client_id 通过 ClientDetailsService#loadClientByClientId(java.lang.String) 方法获取客户端信息
 *  *    其中包括 InMemoryClientDetailsService 从内存获取客户端信息 / JdbcClientDetailsService 从数据库获取客户端信息
 *  * 3、通过 DefaultOAuth2RequestFactory#createTokenRequest() 方法从用户请求中提取信息 [客户端Id、认证类型、作用范围等等]
 *  * 4、生成 Token 流程 ->  token.AbstractTokenGranter#grant() 其中包括校验类型处理 #validateGrantType 、Token 组装 #createAccessToken
 *  * 5、org.springframework.security.authentication.ProviderManager 提供了认证的实现逻辑和流程、负责从所有的认证提供者中找出具体的 provider 进行认证
 *  *
 *  * PS：用户名密码模式与授权码模式差异
 *  *     用户名模式通过直接调用 /oauth/token 获取 access_token 适用于系统自身存在用户信息(需要用户名和密码)
 *  *     验证码模式通过访问 /oauth/authorize 端点获取 code 授权码, 然后调用 /oauth/token 获取 access_token(需要code，不需要用户名密码)
 *
 */


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SelfUserDetailsService selfUserDetailsService;

    @Resource
    private SelfLoginSuccessHandler selfLoginSuccessHandler;

    @Resource
    private SelfLoginFailureHandler selfLoginFailureHandler;

    @Resource
    private SelfLogoutSuccessHandler selfLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 获取用户信息认证
        auth.userDetailsService(selfUserDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")   // 表单登录
                .successHandler(selfLoginSuccessHandler)
                .failureHandler(selfLoginFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(selfLogoutSuccessHandler)
                .and()
                .csrf().disable();
    }
}
