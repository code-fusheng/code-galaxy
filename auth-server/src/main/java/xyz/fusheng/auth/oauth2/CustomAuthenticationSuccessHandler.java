package xyz.fusheng.auth.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.HeaderUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 成功处理器，校验客户端信息、生成jwt令牌，响应result数据
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String HEADER_TYPE = "Basic ";

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功 {}", authentication.getPrincipal());
        // 获取请求头中的客户端信息
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        logger.info("header {}", header);

        // 响应结果对象
        ResultVo result = null;

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
            OAuth2AccessToken accessToken =
                    authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

            result = ResultVo.success(accessToken);
        } catch (Exception e) {
            logger.error("认证成功处理器异常={}", e.getMessage(), e);
            result = new ResultVo(ResultEnums.AUTH_FAILED.getCode(), e.getMessage());
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write( objectMapper.writeValueAsString( result ) );
    }
}
