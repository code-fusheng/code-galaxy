package xyz.fusheng.admin.sys.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.admin.sys.core.service.LoginLogService;
import xyz.fusheng.core.model.sys.entity.LoginLog;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;

/**
 * @FileName: ApiLoginLogController
 * @Author: code-fusheng
 * @Date: 2021/7/8 下午1:18
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/api/loginLog")
public class ApiLoginLogController {

    @Resource
    private LoginLogService loginLogService;

    @RequestMapping(value = "/saveLoginLog", method = RequestMethod.POST)
    public ResultVo<Object> saveLoginLog(@RequestBody LoginLog loginLog) {
        loginLogService.saveLoginLog(loginLog);
        return new ResultVo<>("操作提示: 插入登录日志!");
    }

}
