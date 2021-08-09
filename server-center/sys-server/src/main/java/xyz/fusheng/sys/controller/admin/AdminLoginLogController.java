package xyz.fusheng.sys.controller.admin;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.LoginLog;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.sys.core.service.LoginLogService;

import javax.annotation.Resource;

/**
 * @FileName: AdminLoginLogController
 * @Author: code-fusheng
 * @Date: 2021/8/5 下午3:21
 * @Version: 1.0
 * @Description: 登录日志控制类
 */

@RestController
@RequestMapping("/admin/log/login")
public class AdminLoginLogController {

    @Resource
    private LoginLogService loginLogService;

    @ApiOperation(value = "分页查询登录日志列表")
    @PostMapping("/pageLoginLog")
    public ResultVo<PageData<LoginLog>> pageLoginLog(@RequestBody PageData<LoginLog> page) {
        page = loginLogService.pageLoginLog(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
