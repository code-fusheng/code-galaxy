package xyz.fusheng.auth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @FileName: ReloadMessageConfig
 * @Author: code-fusheng
 * @Date: 2021/6/3 3:52 下午
 * @Version: 1.0
 * @Description: 认证提示信息配置类
 */

@Configuration
public class ReloadMessageConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages_zh_CN");
        return messageSource;
    }

}
