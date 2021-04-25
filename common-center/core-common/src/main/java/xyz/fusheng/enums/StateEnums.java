package xyz.fusheng.enums;

import lombok.Getter;

/**
 * @FileName: StatusEnums
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:32 上午
 * @Version: 1.0
 * @Description: 统一状态枚举
 */

@Getter
public enum StateEnums {

    /**
     * 逻辑删除状态
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态
     */
    ENABLED(1, "启用"),
    NOT_ENABLE(0, "未启用"),

    /**
     * 登录状态
     */
    LOGIN_SUCCESS(0, "登录成功"),
    LOGIN_ERROR(1, "登录失败"),

    /**
     * 请求访问状态枚举
     */
    REQUEST_SUCCESS(1, "请求正常"),
    REQUEST_ERROR(0, "请求异常"),

    /**
     * 消息状态枚举
     */
    MESSAGE_IS_READ(1, "消息已读"),
    MESSAGE_NO_READ(0, "消息未读"),

    /**
     * 选项状态
     */
    OPTION_RIGHT(1, "正确"),
    OPTION_ERROR(0, "错误")

    ;

    /**
     * code 状态码
     * msg 状态信息
     */
    private Integer code;
    private String msg;

    StateEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
