package xyz.fusheng.core.utils;

import com.alibaba.fastjson.JSON;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.sys.entity.LoginLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @FileName: SecurityUtils
 * @Author: code-fusheng
 * @Date: 2021/6/6 3:01 下午
 * @Version: 1.0
 * @Description: 安全工具类
 */

@Slf4j
public class SecurityUtils {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static SelfUser getUserInfo() {
        SelfUser selfUser;
        // 从上下文获取Security认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        logger.info("用户信息:{}", details.getDecodedDetails());
        Map<String, Object> map = (Map<String, Object>)details.getDecodedDetails();
        Map<String, Object> userInfo = (Map<String, Object>) map.get("userInfo");
        selfUser = JSON.parseObject(JSON.toJSONString(userInfo), SelfUser.class);
        return selfUser;
    }

    /**
     * 构造登录信息
     *
     * @param request
     * @return
     */
    public static LoginLog preHandleLoginLogDetail(HttpServletRequest request) {
        LoginLog loginLog = new LoginLog();
        // UserAgent 对象
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取IP地址
        String ipAddress = "";
        try {
            ipAddress = IpUtils.getIpAddr(request);
            log.info("用户登陆获取IP地址信息:{}", ipAddress);
        } catch (Exception e) {
            log.info("{}", e.getMessage(), e);
        }
        loginLog.setIpAddress(ipAddress);
        // 获取操作系统
        String osType = userAgent.getOperatingSystem().getName();
        loginLog.setOsType(osType);
        // 获取浏览器类型
        String browserType = userAgent.getBrowser().getName();
        loginLog.setBrowserType(browserType);
        // 获取登录地址
        String loginLocation = "";
        try {
             loginLocation = AddressUtils.getIpAddressInfo(ipAddress).get("address").toString();
        } catch (Exception e) {
            log.info("{}", e.getMessage(), e);
        }
        log.info("用户登陆获取真实地址信息:{}", loginLocation);
        loginLog.setLoginLocation(loginLocation);
        loginLog.setLoginType(0);
        return loginLog;
    }
}
