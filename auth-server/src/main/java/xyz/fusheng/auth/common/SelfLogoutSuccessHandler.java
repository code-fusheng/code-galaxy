package xyz.fusheng.auth.common;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.tool.utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: SelfLogoutSuccessHandler
 * @Author: code-fusheng
 * @Date: 2021/6/4 5:30 下午
 * @Version: 1.0
 * @Description: 自定义登出成功处理器
 */

@Component("selfLogoutSuccessHandler")
public class SelfLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取 accessToken
        String accessToken = request.getParameter("accessToken");
        if (StringUtils.isNotBlank(accessToken)) {
            // 转换 token 信息
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            if (oAuth2AccessToken != null) {
                // 删除 redis 的访问令牌
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
        }
        SecurityContextHolder.clearContext();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new ResultVo<>("登出成功！")));
    }

}
