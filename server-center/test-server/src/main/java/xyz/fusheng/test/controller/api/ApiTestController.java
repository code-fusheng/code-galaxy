package xyz.fusheng.test.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.vo.ResultVo;

/**
 * @FileName: ApiTestController
 * @Author: code-fusheng
 * @Date: 2021/8/4 下午1:08
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/api/test")
public class ApiTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/testGetPort")
    public ResultVo<Object> testGetPort() {
        return ResultVo.success(port);
    }

}
