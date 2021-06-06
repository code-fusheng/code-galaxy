package xyz.fusheng.auth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: JwtTokenStoreConfig
 * @Author: code-fusheng
 * @Date: 2021/6/3 2:29 下午
 * @Version: 1.0
 * @Description: JWT 令牌管理
 * 1、生成 JWT 访问令牌的时候，将 JWT Token 存入 Redis 中。
 * 2、拓展 JWT 验证功能，验证 redis 是否存在数据，如果存在则 Token 有效，否则无效。
 * 3、退出系统时将 Redis 中的数据删除。
 */

@Configuration
public class JwtTokenStoreConfig {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 定制AccessToken转换器 处理资源服务器无法从JWT获取用户信息的问题
     */
    private class SelfAccessTokenConverter extends DefaultAccessTokenConverter {
        @Override
        public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
            OAuth2Authentication authentication = super.extractAuthentication(claims);
            authentication.setDetails(claims);
            return authentication;
        }
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 设置对称签名
        converter.setSigningKey("fusheng");
        // 定制 AccessToken 转换器
        converter.setAccessTokenConverter(new SelfAccessTokenConverter());
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        // Jwt 管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter()) { // JwtTokenStore匿名类中重写方法
            // 存储到 Redis
            @Override
            public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                if (token.getAdditionalInformation().containsKey("jti")) {
                    String jti = token.getAdditionalInformation().get("jti").toString();
                    redisTemplate.opsForValue()
                            .set(jti, token.getValue(), token.getExpiresIn(), TimeUnit.SECONDS);
                }
                super.storeAccessToken(token, authentication);
            }

            // 删除 redis 的 token
            @Override
            public void removeAccessToken(OAuth2AccessToken token) {
                if (token.getAdditionalInformation().containsKey("jti")) {
                    String jti = token.getAdditionalInformation().toString();
                    redisTemplate.delete(jti);
                }
                super.removeAccessToken(token);
            }
        };
    }

}
