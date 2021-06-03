package xyz.fusheng.auth.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import xyz.fusheng.model.entity.SelfUser;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @FileName: JwtTokenEnhancer
 * @Author: code-fusheng
 * @Date: 2021/6/3 4:11 下午
 * @Version: 1.0
 * @Description: Jwt Token 拓展配置
 */

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        // 拓展令牌内容
        SelfUser user = (SelfUser) oAuth2Authentication.getPrincipal();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("userInfo", JSON.toJSON(user));
        // 设置附加信息
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(map);
        return oAuth2AccessToken;
    }

}
