package xyz.fusheng.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @FileName: SelfUser
 * @Author: code-fusheng
 * @Date: 2021/4/27 12:35 上午
 * @Version: 1.0
 * @Description: 自定义认证授权用户实体类
 */

@Data
public class SelfUser implements Serializable, UserDetails {

    private static final long serialVersionUID = -3084836239369620596L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 头像
     */
    private String header;

    /**
     * 签名
     */
    private String signature;

    /**
     * 介绍 描述
     */
    private String description;

    /**
     * realName 真实名称
     */
    private String realname;

    /**
     * 性别 0:男 1:女
     */
    private Integer sex;

    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 账户是否过期
     */
    private boolean isAccountNonExpired = true;
    /**
     * 账户是否被锁定
     */
    private boolean isAccountNonLocked = true;
    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 账户是否有效
     */
    private boolean isEnabled = true;


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
