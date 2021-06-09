package xyz.fusheng.gateway.core.filter;

import com.alibaba.fastjson.JSON;
import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * @FileName: AccessTokenFilter
 * @Author: code-fusheng
 * @Date: 2021/6/7 9:15 上午
 * @Version: 1.0
 * @Description: 令牌验证过滤器 --- 用于验证请求头访问令牌是否有效,通过Redis查询
 */

@Component
public class AccessTokenFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenFilter.class);

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = StringUtils.substringAfter(authorization, "Bearer ");
        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }
        String message = null;
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JSONObject jsonObject = jwsObject.getPayload().toJSONObject();
            // 查询 redis 是否存在 不存在则过期
            String jti = jsonObject.get("jti").toString();
            Object value = redisTemplate.opsForValue().get(jti);
            if (value == null) {
                logger.info("令牌「{}」已过期!", token);
                message = "您的身份已经过期,请重新认证!";
            }
        } catch (ParseException e) {
            logger.info("令牌「{}」解析错误:{}", token, e);
            message = "无效令牌!";
        }
        if (message == null) {
            return chain.filter(exchange);
        }

        // 响应提示
        JSONObject result = new JSONObject();
        result.put("code", ResultEnums.AUTH_FAILED.getCode());
        result.put("message", message);
        byte[] bits = result.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        return response.writeWith( Mono.just(buffer) );
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
