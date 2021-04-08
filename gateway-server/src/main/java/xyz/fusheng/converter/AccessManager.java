package xyz.fusheng.converter;

import com.alibaba.nacos.common.utils.ConcurrentHashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * @FileName: AccessManager
 * @Author: code-fusheng
 * @Date: 2021/4/8 3:12 下午
 * @Version: 1.0
 * @Description: 权限管理器
 */

@Component
public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(AccessManager.class);

    private Set<String> permitAll = new ConcurrentHashSet<>();

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AccessManager (){
        permitAll.add("/");
        permitAll.add("/error");
        permitAll.add("/favicon.ico");
        permitAll.add("/**/v2/api-docs/**");
        permitAll.add("/**/swagger-resources/**");
        permitAll.add("/webjars/**");
        permitAll.add("/doc.html");
        permitAll.add("/swagger-ui.html");
        permitAll.add("/**/oauth/**");
        permitAll.add("/**/current/get");
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        // 请求资源
        String requestPath = exchange.getRequest().getURI().getPath();
        // 是否直接放行
        if (permitAll(requestPath)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return authenticationMono.map(authentication -> {
            return new AuthorizationDecision(checkAuthorities(exchange, authentication, requestPath));
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

    private boolean permitAll(String requestPath) {
        return permitAll.stream()
                .filter(r -> antPathMatcher.match(r, requestPath)).findFirst().isPresent();
    }

    private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth, String requestPath) {
        if (auth instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) auth;
            String clientId = authentication.getOAuth2Request().getClientId();
            logger.info("clientId is {}", clientId);
        }
        Object principal = auth.getPrincipal();
        logger.info("用户信息:{}",principal.toString());
        return true;
    }

}
