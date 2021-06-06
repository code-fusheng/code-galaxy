package xyz.fusheng.gateway.core.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @FileName: UriKeyResolver
 * @Author: code-fusheng
 * @Date: 2021/6/7 12:14 上午
 * @Version: 1.0
 * @Description: 网关限流过滤器 -- 暂不使用
 */

//@Component("uriKeyResolver")
public class UriKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // 针对微服务的某个请求进行限流
        return Mono.just(exchange.getRequest().getURI().getPath());
    }
}
