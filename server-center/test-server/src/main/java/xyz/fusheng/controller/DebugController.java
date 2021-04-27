package xyz.fusheng.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.feign.UserFeignClientServer;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.ResultVo;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @FileName: DebugController
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:23 上午
 * @Version: 1.0
 * @Description:
 * PS:
 * 问题一: Spring Oauth2.0 通过 Security SecurityContextHolder.getContext().getAuthentication().getPrincipal() 只能获取用户名,拿不到用户Id
 */

@Api(tags = "测试服务-调试接口管理", value = "调试接口控制器")
@RestController
@RequestMapping("/debug")
public class DebugController {

    private static final Logger logger = LoggerFactory.getLogger(DebugController.class);

    @Autowired
    private UserFeignClientServer userServer;

    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "获取调试服务端口号")
    @GetMapping("/getPort")
    public ResultVo<Object> getPort() {
        return new ResultVo<>("操作成功!", port);
    }

    @GetMapping("/getPrincipal")
    public ResultVo<Object> getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("principal:{}", principal);
        User user = userServer.selectUserByUsername(principal.toString());
        logger.info("user:{}", user);
        return new ResultVo<>("操作成功!", user);
    }


}
