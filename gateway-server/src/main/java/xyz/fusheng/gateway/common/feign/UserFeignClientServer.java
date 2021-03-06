package xyz.fusheng.gateway.common.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.fusheng.gateway.common.feign.fallback.UserServiceFallback;
import xyz.fusheng.core.model.entity.User;

/**
 * @FileName: UserService
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:48 上午
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "user-server", path = "/api/user", fallbackFactory = UserServiceFallback.class)
public interface UserFeignClientServer {

    @GetMapping("/selectUserByUsername/{username}")
    User selectUserByUsername(@PathVariable("username") String username);

    @GetMapping("/selectUserByPhone/{phone}")
    User selectUserByPhone(@PathVariable("phone") String phone);

}
