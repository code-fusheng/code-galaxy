package xyz.fusheng.auth.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.auth.service.AuthService;
import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.utils.HeaderUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: SelfAuthenticationSuccessHandler
 * @Author: code-fusheng
 * @Date: 2021/6/4 2:25 下午
 * @Version: 1.0
 * @Description: 自定义认证成功处理器
 */

@Component("selfLoginSuccessHandler")
public class SelfLoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(SelfLoginSuccessHandler.class);

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AuthService authService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    private static final String HEADER_TYPE = "Basic";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("【登录成功】{}", authentication.getPrincipal());
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.info("请求头Header信息预览:{}", header);
        // 封装响应数据
        ResultVo<?> resultVo = null;
        try {
            if(header == null || !header.startsWith(HEADER_TYPE)) {
                throw new UnsupportedOperationException("请求头中无client信息");
            }
            // 解析请求头的客户端信息
            String[] tokens = HeaderUtils.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            // 查询客户端信息，核对是否有效
            ClientDetails clientDetails =
                    clientDetailsService.loadClientByClientId(clientId);
            if(clientDetails == null) {
                throw new UnsupportedOperationException("clientId对应的配置信息不存在：" + clientId);
            }
            // 校验客户端密码是否有效
            if( !passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnsupportedOperationException("无效clientSecret");
            }

            // 组合请求对象，去获取令牌
            TokenRequest tokenRequest =
                    new TokenRequest(MapUtils.EMPTY_MAP, clientId,
                            clientDetails.getScope(), "custom");

            OAuth2Request oAuth2Request =
                    tokenRequest.createOAuth2Request(clientDetails);

            OAuth2Authentication oAuth2Authentication =
                    new OAuth2Authentication(oAuth2Request, authentication);

            // 获取 访问令牌对象
            OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

            resultVo = ResultVo.success(accessToken);
        } catch (Exception e) {
            resultVo = ResultVo.error(ResultEnums.CLIENT_AUTH_FAILED.getMsg());
        }
        // 封装返回参数
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultVo));
    }

}
