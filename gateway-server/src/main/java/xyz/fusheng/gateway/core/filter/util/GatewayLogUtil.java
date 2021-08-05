package xyz.fusheng.gateway.core.filter.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.fusheng.gateway.core.filter.RecorderServerHttpResponseDecorator;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author code-fusheng
 */

public class GatewayLogUtil {
    private final static String REQUEST_RECORDER_LOG_BUFFER = "RequestRecorderGlobalFilter.request_recorder_log_buffer";

    private static boolean hasBody(HttpMethod method) {
        //只记录这3种谓词的body
        if (method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH)
            return true;
        return false;
    }

    private static boolean shouldRecordBody(MediaType contentType) {
        String type = contentType.getType();
        String subType = contentType.getSubtype();
        if ("application".equals(type)) {
            return "json".equals(subType) || "x-www-form-urlencoded".equals(subType) || "xml".equals(subType) || "atom+xml".equals(subType) || "rss+xml".equals(subType);
        } else if ("text".equals(type)) {
            return true;
        }
        //form没有记录
        return false;
    }

    private static Mono<Void> doRecordBody(StringBuffer logBuffer, Flux<DataBuffer> body, Charset charset) {
        return DataBufferUtilFix.join(body)
                .doOnNext(wrapper -> {
                    logBuffer.append(new String(wrapper.getData(), charset));
                    wrapper.clear();
                }).then();
    }

    private static Charset getMediaTypeCharset(@Nullable MediaType mediaType) {
        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        } else {
            return StandardCharsets.UTF_8;
        }
    }

    public static Mono<Void> recorderOriginalRequest(ServerWebExchange exchange) {
        StringBuffer logBuffer = new StringBuffer("请求开始，");
        exchange.getAttributes().put(REQUEST_RECORDER_LOG_BUFFER, logBuffer);
        ServerHttpRequest request = exchange.getRequest();
        return recorderRequest(request, request.getURI(), logBuffer);
    }

    private static Mono<Void> recorderRequest(ServerHttpRequest request, URI uri, StringBuffer logBuffer) {
        if (uri == null) {
            uri = request.getURI();
        }
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();
        logBuffer
                .append("请求路径：").append(uri.getPath())
                .append("，请求方法：").append(method.toString());
        logBuffer.append("，请求头：");
        headers.forEach((name, values) -> {
            values.forEach(value -> {
                if (!name.equalsIgnoreCase("cookie")) {
                    logBuffer.append(name).append(": ").append(value).append("，");
                }
            });
        });
        Charset bodyCharset = null;
        logBuffer.append("请求参数：");
        if (hasBody(method)) {
            long length = headers.getContentLength();
            if (length <= 0) {
                logBuffer.append("null，");
            } else {
                MediaType contentType = headers.getContentType();
                if (contentType == null) {
                    logBuffer.append("null，");
                } else if (!shouldRecordBody(contentType)) {
                    logBuffer.append("null，");
                } else {
                    bodyCharset = getMediaTypeCharset(contentType);
                }
            }
            if (bodyCharset != null) {
                return doRecordBody(logBuffer, request.getBody(), bodyCharset);
            }
        } else {
            Map<String, String> queryParams = request.getQueryParams().toSingleValueMap();
            logBuffer.append(JSONObject.toJSONString(queryParams));
        }
        return Mono.empty();
    }

    public static Mono<Void> recorderResponse(ServerWebExchange exchange) {
        RecorderServerHttpResponseDecorator response = (RecorderServerHttpResponseDecorator) exchange.getResponse();
        StringBuffer logBuffer = exchange.getAttribute(REQUEST_RECORDER_LOG_BUFFER);
        logBuffer.append("\n请求结束，");
        HttpStatus code = response.getStatusCode();
        if (code == null) {
            logBuffer.append("返回异常");
            return Mono.empty();
        }
        logBuffer.append("响应状态码：").append(code.value()).append(" ").append(code.getReasonPhrase());
        HttpHeaders headers = response.getHeaders();
        logBuffer.append("，");
        logBuffer.append("响应结果：");
        Charset bodyCharset = null;
        MediaType contentType = headers.getContentType();
        if (contentType == null) {
            logBuffer.append("null，");
        } else if (!shouldRecordBody(contentType)) {
            logBuffer.append("null，");
        } else {
            bodyCharset = getMediaTypeCharset(contentType);
        }
        if (bodyCharset != null) {
            return doRecordBody(logBuffer, response.copy(), bodyCharset);
        } else {
            return Mono.empty();
        }
    }

    public static String getLogData(ServerWebExchange exchange) {
        StringBuffer logBuffer = exchange.getAttribute(REQUEST_RECORDER_LOG_BUFFER);
        return logBuffer.append("\n").toString();
    }
}
