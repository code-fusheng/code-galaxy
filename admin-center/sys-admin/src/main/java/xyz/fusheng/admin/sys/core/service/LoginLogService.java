package xyz.fusheng.admin.sys.core.service;

import xyz.fusheng.core.model.sys.entity.LoginLog;

/**
 * @FileName: LoginLogService
 * @Author: code-fusheng
 * @Date: 2021/7/7 上午10:51
 * @Version: 1.0
 * @Description:
 */

public interface LoginLogService {

    void saveLoginLog(LoginLog loginLog);

}
