package xyz.fusheng.user.controller.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.user.core.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2021/6/6 11:37 上午
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Resource
    private UserService userService;

    /**
     * >>>>> Feign 远程调用接口 >>>>
     */

    @ApiOperation(value = "通过用户名获取用户信息-Feign接口")
    @GetMapping("/selectUserByUsername/{username}")
    public User selectUserByUsername(@PathVariable("username") String username) {
        return userService.selectUserByUsername(username);
    }

    @ApiOperation(value = "通过手机号获取用户信息-Feign接口")
    @GetMapping("/selectUserByPhone/{phone}")
    public User selectUserByPhone(@PathVariable("phone") String phone) {
        return userService.selectUserByPhone(phone);
    }

    @ApiOperation(value = "通过用户Id获取用户角色列表")
    @GetMapping("/selectRolesByUserId/{userId}")
    public List<Role> selectRolesByUserId(@PathVariable Long userId) {
        return userService.selectRolesByUserId(userId);
    }

    @ApiOperation(value = "通过用户Id获取用户权限列表")
    @GetMapping("/selectMenusByUserId/{userId}")
    public List<Menu> selectMenusByUserId(@PathVariable("userId") Long userId) {
        return userService.selectMenusByUserId(userId);
    }

}
