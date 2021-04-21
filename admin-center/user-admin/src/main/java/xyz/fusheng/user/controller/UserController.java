package xyz.fusheng.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.user.service.UserService;

import javax.annotation.Resource;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2021/4/12 3:12 下午
 * @Version: 1.0
 * @Description: 用户后台控制类
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理后台接口", value = "用户管理后台接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/saveUser")
    @ApiOperation(value = "添加用户", notes = "后台添加用户")
    public ResultVo<Object> saveUser(@RequestBody @Validated UserDto userDto) {
        userService.saveUser(userDto);
        return new ResultVo<>("操作成功:添加用户!");
    }

}
