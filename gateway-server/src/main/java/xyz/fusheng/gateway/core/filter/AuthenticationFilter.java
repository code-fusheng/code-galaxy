package xyz.fusheng.gateway.core.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xyz.fusheng.core.enums.ResultEnums;

import java.nio.charset.StandardCharsets;

/**
 * @FileName: AuthenticationFilter
 * @Author: code-fusheng
 * @Date: 2021/6/7 12:18 上午
 * @Version: 1.0
 * @Description: 认证过滤器 --- API 网关统一拦截器，用于验证请求是否有 Authorization
 */

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    /**
     * 白名单: 直接放行请求前缀
     */
    private static final String[] whites = {"/api/", ""};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 请求路径
        String path = request.getPath().pathWithinApplication().value();
        logger.info("发送 {} 请求到 {}", request.getMethod(), path);
        // 公开 API 白名单接口无需认证
        if (StringUtils.indexOfAny(path, whites) != -1) {
            // 直接放行
            return chain.filter(exchange);
        }
        // 获取请求头中 key 为 Authorization 的值
        // 获取 token 时，要带上 Authorization : Basic client_id:client_secret
        // 请求应用接口时，要带上 Authorization : Bearer token
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 如果请求路径中不存在，不转发请求，响应提示:
        if (StringUtils.isEmpty(authorization)) {
            // 响应消息内容对象
            JSONObject message = new JSONObject();
            // 响应状态
            message.put("code", "10005");
            message.put("message", "缺少身份凭证!");
            // 转换响应消息内容对象为字节
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            // 设置响应对象状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 设置响应对象内容并且指定编码,否则在浏览器中会出现中文乱码
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            // 返回响应对象
            return response.writeWith( Mono.just(buffer) );
        }
        logger.info("请求头有 Authorization 放行请求!");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 过滤器执行优先级, 越小越优先
        return 0;
    }
}
