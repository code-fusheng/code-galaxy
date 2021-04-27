package xyz.fusheng.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.fusheng.feign.fallback.UserServiceFallback;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.entity.User;

import java.util.List;

/**
 * @FileName: UserService
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:48 上午
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "user-server", fallbackFactory = UserServiceFallback.class)
public interface UserFeignClientServer {

    @GetMapping("/api/user/selectUserByUsername/{username}")
    User selectUserByUsername(@PathVariable("username") String username);

    @GetMapping("/api/user/selectUserByPhone/{phone}")
    User selectUserByPhone(@PathVariable("phone") String phone);

    @GetMapping("/api/user/selectRolesByUserId/{userId}")
    List<Role> selectRolesByUserId(@PathVariable("userId") Long userId);

    @GetMapping("/api/user/selectMenusByUserId/{userId}")
    List<Menu> selectMenusByUserId(@PathVariable("userId") Long userId);

    @GetMapping("/api/menu/getMenuByPath/{path}")
    Menu getMenuByPath(@PathVariable("path") String path);
}
