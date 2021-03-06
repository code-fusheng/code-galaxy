package xyz.fusheng.gateway.core.mamager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import reactor.core.publisher.Mono;
import org.springframework.security.oauth2.provider.token.TokenStore;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.exception.BusinessException;

/**
 * @FileName: ReactiveDbAuthenticationManager
 * @Author: code-fusheng
 * @Date: 2021/4/8 2:49 下午
 * @Version: 1.0
 * @Description: 自定义认证接口管理类
 */

public class CustomAuthenticationManager implements ReactiveAuthenticationManager {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationManager.class);

    public TokenStore tokenStore;

    public CustomAuthenticationManager(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessTokenValue -> {
                    try {
                        OAuth2AccessToken accessToken = tokenStore.readAccessToken(accessTokenValue);
                        if (accessToken == null) {
                            return Mono.error(new InvalidTokenException("Invalid access token: " + accessTokenValue));
                        } else if (accessToken.isExpired()) {
                            tokenStore.removeAccessToken(accessToken);
                            return Mono.error(new InvalidTokenException("Access token expired: " + accessTokenValue));
                        }

                        OAuth2Authentication result = tokenStore.readAuthentication(accessToken);
                        if (result == null) {
                            return Mono.error(new InvalidTokenException("Invalid access token: " + accessTokenValue));
                        }
                        return Mono.just(result);
                    } catch (InvalidTokenException e) {
                        throw new BusinessException(ResultEnum.AUTH_FAILED.getCode(), "Token 非法");
                    }
                }))
                .cast(Authentication.class);
    }
}
