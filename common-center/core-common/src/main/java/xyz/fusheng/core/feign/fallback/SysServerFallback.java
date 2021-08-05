package xyz.fusheng.core.feign.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import xyz.fusheng.core.feign.SysFeignClientServer;
import xyz.fusheng.core.model.entity.LoginLog;

/**
 * @FileName: SysServerFallback
 * @Author: code-fusheng
 * @Date: 2021/7/8 上午9:16
 * @Version: 1.0
 * @Description:
 */

@Slf4j
public class SysServerFallback implements FallbackFactory<SysFeignClientServer> {
    @Override
    public SysFeignClientServer create(Throwable throwable) {
        return new SysFeignClientServer() {
            @Override
            public void saveLoginLog(LoginLog loginLog) {
                log.error("调用sys-admin-server服务-插入登录日志:{} 失败!", loginLog, throwable);
            }
        };
    }
}
