package xyz.fusheng.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.model.vo.RoleVo;
import xyz.fusheng.model.vo.UserVo;
import xyz.fusheng.user.service.UserService;
import xyz.fusheng.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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

    @ApiOperation(value = "删除用户根据Id")
    @DeleteMapping("/deleteUserById/{userId}")
    public ResultVo<Object> deleteUserById(@PathVariable @Validated @ApiParam(value = "用户Id", required = true, example = "")
                                                       Long userId) {
        userService.deleteUserById(userId);
        return new ResultVo<>("操作提示: 删除用户!");
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("/getUserByPage")
    public ResultVo<Page<UserVo>> getUserByPage(@RequestBody Page<UserVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 创建时间、更新时间
            String[] sortColumns = {"created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = userService.getUserByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
