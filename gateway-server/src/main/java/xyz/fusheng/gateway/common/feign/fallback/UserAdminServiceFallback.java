package xyz.fusheng.gateway.common.feign.fallback;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.fusheng.gateway.common.feign.UserAdminFeignClientServer;
import xyz.fusheng.core.model.entity.Menu;

/**
 * @FileName: UserAdminServiceFallback
 * @Author: code-fusheng
 * @Date: 2021/4/14 11:27 上午
 * @Version: 1.0
 * @Description:
 */

public class UserAdminServiceFallback implements FallbackFactory<UserAdminFeignClientServer> {

    public static final Logger logger = LoggerFactory.getLogger(UserAdminServiceFallback.class);

    @Override
    public UserAdminFeignClientServer create(Throwable throwable) {
        return new UserAdminFeignClientServer() {
            @Override
            public Menu getMenuByPath(String path) {
                logger.error("调用user-admin-server服务-通过用户名:{}查询用户失败", path, throwable);
                return null;
            }
        };
    }
}
