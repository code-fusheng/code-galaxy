package xyz.fusheng.admin.sys.core.service.impl;

import org.springframework.stereotype.Service;
import xyz.fusheng.admin.sys.core.mapper.LoginLogMapper;
import xyz.fusheng.admin.sys.core.service.LoginLogService;
import xyz.fusheng.core.model.sys.entity.LoginLog;

import javax.annotation.Resource;

/**
 * @FileName: LoginLogServiceImpl
 * @Author: code-fusheng
 * @Date: 2021/7/7 上午10:52
 * @Version: 1.0
 * @Description:
 */

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }
}
