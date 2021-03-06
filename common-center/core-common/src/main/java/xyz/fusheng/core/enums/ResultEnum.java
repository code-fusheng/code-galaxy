package xyz.fusheng.core.enums;

import lombok.Getter;

/**
 * @FileName: ResultEnums
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:30 上午
 * @Version: 1.0
 * @Description: HTTP 返回结果状态码枚举
 */

@Getter
public enum ResultEnum {

    /**
     * 标准 HTTP 异常
     * 返回枚举类型，每一个枚举类型代表一个返回状态
     * 1** 信息，服务器收到请求，需要请求这继续执行操作
     * 2** 成功，操作被成功执行并接受处理
     * 3** 重定向，需要进一步的操作已完成请求
     * 4** 客户端错误，请求包汉语发错误或无法完成请求
     * 5** 服务器错误，服务在处理请求过程中发生了错误
     */

    SUCCESS(200, "操作成功"),
    ERROR(400, "请求错误"),
    NOT_LOGIN(401, "未登录"),
    NOT_AUTHORIZED(403, "未授权"),
    NOT_FOUND(404,"访问连接不存在"),
    INTERNAL_SERVER_ERROR(500,"服务器故障"),

    /**
     * Oauth 认证相关异常
     */
    CLIENT_AUTH_FAILED(10001, "客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(10002, "用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(10003, "不支持的认证模式"),
    AUTH_FAILED(10004, "认证失败"),
    MISSING_CREDENTIALS(10005, "缺少身份凭证"),
    TOKEN_PAST(10006, "认证失效"),

    /**
     * 系统自定义异常
     */
    BUSINESS_ERROR(99999, "统一业务异常")
    ;

    /**
     * code 响应码
     * msg 响应信息
     */
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
