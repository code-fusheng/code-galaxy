package xyz.fusheng.tool.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import xyz.fusheng.core.model.entity.SelfUser;

import java.util.Map;

/**
 * @FileName: SecurityUtils
 * @Author: code-fusheng
 * @Date: 2021/6/6 3:01 下午
 * @Version: 1.0
 * @Description: 安全工具类
 */

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
}
