package xyz.fusheng.sms;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * @FileName: SmsCodeAuthenticationSecurityConfig
 * @Author: code-fusheng
 * @Date: 2021/4/9 2:05 下午
 * @Version: 1.0
 * @Description: 短信验证 SmsCode 配置类
 * 用于实现 SmsCodeAuthenticationProvider 的注入 否则无法被 ProviderManager 选择
 */

@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        // 注入 SmsCodeAuthenticationProvider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        builder.authenticationProvider(smsCodeAuthenticationProvider);
    }
}
