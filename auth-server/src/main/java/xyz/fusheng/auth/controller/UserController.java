package xyz.fusheng.auth.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.tool.feign.UserFeignClientServer;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2021/4/8 11:58 上午
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserFeignClientServer userService;

    @GetMapping("/getUserByUsername/{username}")
    public ResultVo<User> getUserByUsername(@PathVariable String username) {
        User user = userService.selectUserByUsername(username);
        return new ResultVo<>("操作成功!", user);
    }

    /**
     * 获取授权用户信息
     * @param principal
     * @return
     */
    @GetMapping("/current/get")
    public Principal user(Principal principal) {
        return principal;
    }

    @ApiOperation(value = "当前登录用户信息")
    @GetMapping("/info")
    public User info() {
        // TODO 当前只能拿到用户名信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("{}", principal);
        User user = userService.selectUserByUsername(principal.toString());
        return user;
    }

}
