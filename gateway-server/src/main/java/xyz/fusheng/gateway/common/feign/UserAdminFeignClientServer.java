package xyz.fusheng.gateway.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.fusheng.gateway.common.feign.fallback.UserAdminServiceFallback;
import xyz.fusheng.core.model.entity.Menu;

/**
 * @FileName: UserAdminFeignClientServer
 * @Author: code-fusheng
 * @Date: 2021/4/14 11:26 上午
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "user-admin-server", path = "/api/menu" ,fallbackFactory = UserAdminServiceFallback.class)
public interface UserAdminFeignClientServer {

    @GetMapping("/getMenuByPath")
    Menu getMenuByPath(@RequestParam("path") String path);

}
