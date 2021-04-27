package xyz.fusheng.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @FileName: FeignRequestInterceptor
 * @Author: code-fusheng
 * @Date: 2021/4/8 4:26 下午
 * @Version: 1.0
 * @Description: 微服务 Feign 调用请求头丢失问题
 */

@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if (httpServletRequest != null) {
            Map<String, String> headers = getHeaders(httpServletRequest);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                template.header(entry.getKey(), entry.getValue());
            }
//            logger.info("FeignRequestInterceptor:{}", template);
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }
}
