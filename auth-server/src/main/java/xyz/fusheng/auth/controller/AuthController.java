package xyz.fusheng.auth.controller;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHeaders;
import org.apache.tomcat.util.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.auth.service.AuthService;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.utils.HeaderUtils;
import xyz.fusheng.utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @FileName: AuthController
 * @Author: code-fusheng
 * @Date: 2021/6/3 6:37 下午
 * @Version: 1.0
 * @Description:
 */

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private AuthService authService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ClientDetailsService clientDetailsService;

    private static final String HEADER_TYPE = "Basic";

    @GetMapping("/user/refreshToken")
    public ResultVo refreshToken(HttpServletRequest request) {
        try {
            // 获取请求中的刷新令牌
            String refreshToken = (String) request.getParameter("refreshToken");
            Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "刷新令牌不能为空!");
            // 请求头
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith(HEADER_TYPE)) {
                throw new UnapprovedClientAuthenticationException("请求头中无Client信息!");
            }
            String[] tokens = HeaderUtils.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (ObjectUtils.isEmpty(clientDetails)) {
                throw new UnapprovedClientAuthenticationException("clientId 对应的配置信息不存在:" + clientId);
            }
            // 客户端密码是否正确
            if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("无效的clientSecret!");
            }
            // 获取刷新令牌
            return authService.refreshToken(header, refreshToken);
        } catch (Exception e) {
            logger.error("refreshToken={}", e.getMessage(), e);
            return ResultVo.error("新令牌获取失败:" + e.getMessage());
        }
    }

}
