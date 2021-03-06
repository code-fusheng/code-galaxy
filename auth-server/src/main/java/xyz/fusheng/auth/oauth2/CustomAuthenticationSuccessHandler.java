package xyz.fusheng.auth.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.model.entity.LoginLog;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.HeaderUtils;
import xyz.fusheng.core.utils.SecurityUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 成功处理器，校验客户端信息、生成jwt令牌，响应result数据
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String HEADER_TYPE = "Basic ";

    @Resource
    private Environment environment;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

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
            result = new ResultVo(ResultEnum.AUTH_FAILED.getCode(), e.getMessage());
        }

        SelfUser userInfo = (SelfUser) authentication.getPrincipal();
        logger.info("用户信息:{}", userInfo);

        LoginLog loginLog = SecurityUtils.preHandleLoginLogDetail(request);
        loginLog.setUserId(userInfo.getUserId());
        loginLog.setUserName(userInfo.getUsername());
        loginLog.setLoginTime(new Date());
        loginLog.setMsg("登录成功！");
        loginLog.setLoginStatus(StateEnums.LOGIN_SUCCESS.getCode());

        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(environment.getProperty("spring.profiles.active")+".log.login-address.exchange");
            rabbitTemplate.setRoutingKey(environment.getProperty("spring.profiles.active")+".log.login-address.routing-key");
            Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(loginLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
            rabbitTemplate.convertAndSend(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write( objectMapper.writeValueAsString( result ) );
    }
}
