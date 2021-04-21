package xyz.fusheng.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.ConcurrentHashSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xyz.fusheng.constants.AuthConstants;
import xyz.fusheng.constants.CodeConstant;
import xyz.fusheng.exception.BusinessException;
import xyz.fusheng.feign.UserAdminFeignClientServer;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.vo.ResultVo;

import javax.annotation.Resource;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserAdminFeignClientServer userAdminFeignClientServer;

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

    /**
     * 实现权限验证判断
     * @param authenticationMono
     * @param authorizationContext
     * @return
     */
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        // 请求资源
        String requestPath = exchange.getRequest().getURI().getPath();
        // 是否直接放行
        if (permitAll(requestPath)) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 1、对应跨域的预检请求直接放行
        if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 2、Token 为空拒绝访问
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }

        // 3、缓存取资源权限角色关系 通过请求路径获取需要的角色和权限
        logger.info("当前请求路径:{}", requestPath);
        // TODO 走缓存
        this.getMenuByPathForRedis(requestPath);


        return authenticationMono.map(authentication -> {
            return new AuthorizationDecision(checkAuthorities(exchange, authentication, requestPath));
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 校验是否属于静态资源
     * @param requestPath
     * @return
     */
    private boolean permitAll(String requestPath) {
        return permitAll.stream()
                .filter(r -> antPathMatcher.match(r, requestPath)).findFirst().isPresent();
    }

    /**
     * 权限校验
     * @param exchange
     * @param auth
     * @param requestPath
     * @return
     */
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

    /**
     * 通过请求路径从Redis缓存获取角色权限配置
     * @param path
     * @return
     */
    public Menu getMenuByPathForRedis(String path) {
        String key = CodeConstant.REDIS_MENU_PREFIX + path;
        Menu menu = new Menu();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object o = opsForValue.get(key);
        if (ObjectUtils.isEmpty(o)) {
            menu = this.syncSingleMenuToRedis(path);
            logger.info("MySQL - Menu:{}", menu);
            return menu;
        }
        menu = JSON.parseObject(o.toString(), Menu.class);
        logger.info("Redis - Menu:{}", menu);
        return menu;
    }

    /**
     * 同步单条权限数据至Redis缓存
     * @param path
     * @return
     */
    public Menu syncSingleMenuToRedis(String path) {
        Menu menu = userAdminFeignClientServer.getMenuByPath(path);
        if (ObjectUtils.isEmpty(menu)) {
            throw new BusinessException("权限信息为空!");
        }
        try {
            redisTemplate.opsForValue().set(CodeConstant.REDIS_MENU_PREFIX + path, JSON.toJSONString(menu));
        } catch (Exception e) {
            logger.info("权限:{} Redis 缓存数据异常, 异常信息:", path, e);
            return null;
        }
        return menu;
    }

}
