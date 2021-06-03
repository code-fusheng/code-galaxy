package xyz.fusheng.auth.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exception.BusinessException;
import xyz.fusheng.feign.AuthFeignClientServer;
import xyz.fusheng.model.dto.LoginDto;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2021/4/8 10:04 上午
 * @Version: 1.0
 * @Description: 用户控制器
 */

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private AuthFeignClientServer authFeignClientServer;

    @GetMapping("/user/debugUser")
    public ResultVo<Object> debugUser() {
        return new ResultVo<>("操作成功!");
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVo<Object> login(@RequestBody @Validated LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("{}", loginDto);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        Integer loginType = loginDto.getLoginType();
        Assert.notNull(loginType, "登录方式不为空!");
        switch (loginType) {
            case 0:
                map.add("grant_type", "password");
                map.add("username", loginDto.getUsername());
                map.add("password", loginDto.getPhone());
                break;
            case 1:
                map.add("grant_type", "mobile");
            default:
                throw new BusinessException("不支持的登录方式!");
        }
        map.add("client_id", "test-server");
        map.add("client_secret", "test");
        Map<String, String> resultMap = authFeignClientServer.getAccessToken("test-server", "test", "password", loginDto.getUsername(), loginDto.getPassword());
        logger.info("{}", resultMap);
        String access_token = resultMap.get("access_token");
//        response.setHeader("authorization", "Bearer " + access_token );
        return new ResultVo<>("登录成功!", access_token);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultVo<User> info() {
        User userInfo = authFeignClientServer.info();
        return new ResultVo<>("操作提示: 获取成功!", userInfo);
    }


    /**
     * >>>>> Feign 远程调用接口 >>>>
     */

    @ApiOperation(value = "通过用户名获取用户信息-Feign接口")
    @GetMapping("/api/user/selectUserByUsername/{username}")
    public User selectUserByUsername(@PathVariable("username") String username) {
        return userService.selectUserByUsername(username);
    }

    @ApiOperation(value = "通过手机号获取用户信息-Feign接口")
    @GetMapping("/api//user/selectUserByPhone/{phone}")
    public User selectUserByPhone(@PathVariable("phone") String phone) {
        return userService.selectUserByPhone(phone);
    }

    @ApiOperation(value = "通过用户Id获取用户角色列表")
    @GetMapping("/api//user/selectRolesByUserId/{userId}")
    public List<Role> selectRolesByUserId(@PathVariable Long userId) {
        return userService.selectRolesByUserId(userId);
    }

    @ApiOperation(value = "通过用户Id获取用户权限列表")
    @GetMapping("/api//user/selectMenusByUserId/{userId}")
    public List<Menu> selectMenusByUserId(@PathVariable("userId") Long userId) {
        return userService.selectMenusByUserId(userId);
    }

}
