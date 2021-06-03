package xyz.fusheng.auth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @FileName: PasswordEncoderConfig
 * @Author: code-fusheng
 * @Date: 2021/6/3 2:21 下午
 * @Version: 1.0
 * @Description: BCrypt 加密配置类
 */

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
