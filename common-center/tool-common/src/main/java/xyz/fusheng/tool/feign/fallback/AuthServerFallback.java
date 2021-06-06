package xyz.fusheng.tool.feign.fallback;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.fusheng.tool.feign.AuthFeignClientServer;
import xyz.fusheng.core.model.entity.User;

import java.util.Map;

/**
 * @FileName: AuthFeignFallback
 * @Author: code-fusheng
 * @Date: 2021/4/27 10:51 上午
 * @Version: 1.0
 * @Description:
 */

public class AuthServerFallback implements FallbackFactory<AuthFeignClientServer> {

    public static final Logger logger = LoggerFactory.getLogger(AuthServerFallback.class);

    @Override
    public AuthFeignClientServer create(Throwable throwable) {
        return new AuthFeignClientServer() {
            @Override
            public Map<String, String> getAccessToken(String clientId, String secret, String grantType, String username, String password) {
                logger.error("调用auth-server服务-获取access_token失败", username, throwable);
                return null;
            }

            @Override
            public User info() {
                logger.error("调用auth-server服务-获取用户信息失败", throwable);
                return null;
            }
        };
    }
}
