package xyz.fusheng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.feign.UserFeignClientServer;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.ResultVo;

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

}
