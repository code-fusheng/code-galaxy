package xyz.fusheng.core.model.entity;

import lombok.Data;

/**
 * @FileName: UserInfo
 * @Author: code-fusheng
 * @Date: 2021/8/4 下午5:47
 * @Version: 1.0
 * @Description: 用户认证基础信息
 */

@Data
public class UserInfo {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * realName 真实名称
     */
    private String realname;

}
