package xyz.fusheng.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.model.vo.ResultVo;

/**
 * @FileName: DebugController
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:23 上午
 * @Version: 1.0
 * @Description:
 */

@Api(tags = "测试服务-调试接口管理", value = "调试接口控制器")
@RestController
@RequestMapping("/debug")
public class DebugController {

    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "获取调试服务端口号")
    @GetMapping("/getPort")
    public ResultVo<Object> getPort() {
        return new ResultVo<>("操作成功!", port);
    }

}
