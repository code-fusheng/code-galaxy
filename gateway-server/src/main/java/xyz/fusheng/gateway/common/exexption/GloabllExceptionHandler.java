package xyz.fusheng.gateway.common.exexption;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.ServiceUnavailableException;
import java.util.HashMap;
import java.util.Map;


/**
 * @FileName: GlobalExceptionHandler
 * @Author: code-fusheng
 * @Date: 2021/4/12 4:52 下午
 * @Version: 1.0
 * @Description: 全局异常处理器
 * @RestControllerAdvice 包含 @ControllerAdvice注解和@ResponseBody注解
 */

@Slf4j
public class GloabllExceptionHandler extends DefaultErrorWebExceptionHandler {

    public GloabllExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                   ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 处理异常数据
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = super.getError(request);
        Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String msg = "系统错误";
        StringBuilder message = new StringBuilder("Failed to handle request [").append(request.methodName()).append(" ").append(request.uri()).append("]");
        if (error != null) {
            message.append(": ").append(error.getMessage());
            if (error instanceof NotFoundException) {
                msg = "服务未找到";
                code = HttpStatus.NOT_FOUND.value();
            } else if (error instanceof ResponseStatusException) {
                ResponseStatusException responseStatusException = (ResponseStatusException) error;
                if (responseStatusException.getStatus().equals(HttpStatus.NOT_FOUND)) {
                    msg = "服务未找到";
                    code = HttpStatus.NOT_FOUND.value();
                }
            } else if (error instanceof ServiceUnavailableException) {
                ServiceUnavailableException serviceUnavailableException = (ServiceUnavailableException) error;
                code = HttpStatus.SERVICE_UNAVAILABLE.value();
                return response(code, "服务不可用", JSONObject.parse(serviceUnavailableException.getMessage()));
            }
        }
        log.error("【网关发生异常....info...】" + message);
        return response(code, msg);
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus
     */
    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = 0;
        if (errorAttributes.containsKey("code")) {
            statusCode = (int) errorAttributes.get("code");
        } else {
            statusCode = (int) errorAttributes.get("status");
        }
        return statusCode;
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param status       状态码
     * @param errorMessage 异常信息
     */
    public static Map<String, Object> response(int status, String errorMessage, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status);
        map.put("message", errorMessage);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status);
        map.put("message", errorMessage);
        map.put("data", null);
        return map;
    }
}
