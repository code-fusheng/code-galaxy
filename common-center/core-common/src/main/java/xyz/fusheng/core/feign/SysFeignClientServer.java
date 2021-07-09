package xyz.fusheng.core.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.fusheng.core.feign.fallback.SysServerFallback;
import xyz.fusheng.core.model.sys.entity.LoginLog;

/**
 * @FileName: LoginLogFeignClientServer
 * @Author: code-fusheng
 * @Date: 2021/7/8 上午9:14
 * @Version: 1.0
 * @Description:
 */

@FeignClient(name = "sys-admin-server", fallbackFactory = SysServerFallback.class)
public interface SysFeignClientServer {

    @RequestMapping(value = "/api/loginLog/saveLoginLog", method = RequestMethod.POST)
    void saveLoginLog(@RequestBody LoginLog loginLog);

}
