package xyz.fusheng.controller;

import org.springframework.web.bind.annotation.*;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.service.UserService;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2021/4/8 10:04 上午
 * @Version: 1.0
 * @Description: 用户控制器
 */

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user/debugUser")
    public ResultVo<Object> debugUser() {
        return new ResultVo<>("操作成功!");
    }

    /**
     * >>>>> Feign 远程调用接口 >>>>
     * @param username
     * @return
     */
    @GetMapping("/user/selectUserByUsername/{username}")
    public User selectUserByUsername(@PathVariable("username") String username) {
        User user = userService.selectUserByUsername(username);
        return user;
    }

    @GetMapping("/user/selectRolesByUserId/{userId}")
    public List<Role> selectRolesByUserId(@PathVariable Long userId) {
        List<Role> roleList = userService.selectRolesByUserId(userId);
        return roleList;
    }

}
