package xyz.fusheng.sms;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.fusheng.service.SelfUserDetailsService;

import javax.servlet.http.HttpServletRequest;

/**
 * @FileName: SmsCodeAuthenticationProvider
 * @Author: code-fusheng
 * @Date: 2021/4/9 1:24 下午
 * @Version: 1.0
 * @Description: 短信登陆认证鉴权 Provider 提供者
 * 实现 AuthenticationProvider
 */

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(SmsCodeAuthenticationProvider.class);

    private SelfUserDetailsService selfUserDetailsService;

    /**
     * 处理验证逻辑
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 强转 authentication 为 smsCodeAuthenticationToken
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;

        String phone = (String) smsCodeAuthenticationToken.getPrincipal();

        //TODO 校验手机号 Redis 缓存验证码 key 手机号
        checkSmsCode(phone);

        UserDetails userDetails = selfUserDetailsService.loadUserByPhone(phone);

        if (ObjectUtils.isEmpty(userDetails)) {
            throw new InternalAuthenticationServiceException("短信登陆错误:用户不存在!");
        }
        return new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    /**
     * 临时手机号验证码校验
     * @param phone
     */
    private void checkSmsCode(String phone) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求里的手机号与验证码
        String smsCode = request.getParameter("smsCode");

        if (StringUtils.isEmpty(smsCode) || !"000000".equals(smsCode)) {
            throw new BadCredentialsException("短信登陆错误:验证码不匹配!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public SelfUserDetailsService getSelfUserDetailsService() {
        return selfUserDetailsService;
    }

    public void setSelfUserDetailsService(SelfUserDetailsService selfUserDetailsService) {
        this.selfUserDetailsService = selfUserDetailsService;
    }

}
