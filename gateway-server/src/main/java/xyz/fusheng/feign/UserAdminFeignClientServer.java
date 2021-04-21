package xyz.fusheng.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.fusheng.feign.fallback.UserAdminServiceFallback;
import xyz.fusheng.feign.fallback.UserServiceFallback;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.vo.ResultVo;

/**
 * @FileName: UserAdminFeignClientServer
 * @Author: code-fusheng
 * @Date: 2021/4/14 11:26 上午
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "user-admin-server", fallbackFactory = UserAdminServiceFallback.class)
public interface UserAdminFeignClientServer {

    @GetMapping("/menu/getMenuByPath")
    Menu getMenuByPath(@RequestParam("path") String path);

}
