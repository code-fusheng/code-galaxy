package xyz.fusheng.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.fusheng.feign.fallback.AuthServerFallback;
import xyz.fusheng.model.entity.User;

import java.util.Map;

/**
 * @FileName: AuthFeignClientServer
 * @Author: code-fusheng
 * @Date: 2021/4/27 10:51 上午
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "auth-server", fallbackFactory = AuthServerFallback.class)
public interface AuthFeignClientServer {

    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    Map<String, String> getAccessToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String secret,
            @RequestParam("grant_type") String grantType,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    User info();

}
