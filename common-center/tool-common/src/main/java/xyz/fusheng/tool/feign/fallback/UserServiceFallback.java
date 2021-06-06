package xyz.fusheng.tool.feign.fallback;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.fusheng.tool.feign.UserFeignClientServer;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;

import java.util.List;

/**
 * @FileName: UserServiceFallback
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:51 上午
 * @Version: 1.0
 * @Description:
 */

public class UserServiceFallback implements FallbackFactory<UserFeignClientServer> {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceFallback.class);

    @Override
    public UserFeignClientServer create(Throwable throwable) {
        return new UserFeignClientServer() {
            @Override
            public User selectUserByUsername(String username) {
                logger.error("调用user-server服务-通过用户名:{}查询用户失败", username, throwable);
                return null;
            }
            @Override
            public User selectUserByPhone(String phone) {
                logger.error("调用user-server服务-通过手机号:{}查询用户失败:{}", phone, throwable);
                return null;
            }

            @Override
            public List<Role> selectRolesByUserId(Long userId) {
                logger.error("调用user-server服务-通过用户Id:{}查询用户角色失败:{}", userId, throwable);
                return null;
            }

            @Override
            public List<Menu> selectMenusByUserId(Long userId) {
                logger.error("调用user-server服务-通过用户Id:{}查询用户权限失败:{}", userId, throwable);
                return null;
            }

        };
    }
}
