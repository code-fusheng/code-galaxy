package xyz.fusheng.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @FileName: SmsCodeAuthenticationToken
 * @Author: code-fusheng
 * @Date: 2021/4/9 1:08 下午
 * @Version: 1.0
 * @Description: 短信登陆认证逻辑
 * 参考实现 org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 *
 */

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 在 UsernamePasswordAuthenticationToken 中该字段代表登录的用户名，
     * 在这里就代表登录的手机号码
     */
    private final Object principal;

    /**
     * 构建一个没有鉴权的 SmsCodeAuthenticationToken
     * @param principal 手机号
     */
    public SmsCodeAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * 构建一个拥有鉴权的 SmsCodeAuthenticationToken
     * @param principal
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object principal,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        // 重写必须用 super
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException{
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "无法将此令牌设置为trusted-use构造函数，该构造函数改为接受GrantedAuthority列表");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
