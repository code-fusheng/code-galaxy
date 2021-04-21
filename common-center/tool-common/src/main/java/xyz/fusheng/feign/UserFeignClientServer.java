package xyz.fusheng.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.fusheng.feign.fallback.UserServiceFallback;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.entity.User;

/**
 * @FileName: UserService
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:48 上午
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "user-server", fallbackFactory = UserServiceFallback.class)
public interface UserFeignClientServer {

    @GetMapping("/user/selectUserByUsername/{username}")
    User selectUserByUsername(@PathVariable("username") String username);

    @GetMapping("/user/selectUserByPhone/{phone}")
    User selectUserByPhone(@PathVariable("phone") String phone);

    @GetMapping("/menu/getMenuByPath/{path}")
    Menu getMenuByPath(@PathVariable("path") String path);
}
