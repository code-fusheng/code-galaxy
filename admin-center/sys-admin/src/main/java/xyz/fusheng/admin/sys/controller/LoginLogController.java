package xyz.fusheng.admin.sys.controller;

import org.springframework.web.bind.annotation.*;
import xyz.fusheng.admin.sys.core.service.LoginLogService;
import xyz.fusheng.core.model.sys.entity.LoginLog;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;

/**
 * @FileName: LoginLogController
 * @Author: code-fusheng
 * @Date: 2021/7/7 上午10:50
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService;



}
