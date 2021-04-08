package xyz.fusheng.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fusheng.feign.fallback.UserServiceFallback;
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

}
