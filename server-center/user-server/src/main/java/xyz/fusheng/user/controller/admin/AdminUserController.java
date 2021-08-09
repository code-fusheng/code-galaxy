package xyz.fusheng.user.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.dto.UserDto;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.model.vo.UserVo;
import xyz.fusheng.core.utils.StringUtils;
import xyz.fusheng.user.common.annotation.UserInfo;
import xyz.fusheng.user.core.service.UserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: UserAdminController
 * @Author: code-fusheng
 * @Date: 2021/7/12 下午2:40
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/admin/user")
@Api(tags = "用户管理后台接口", value = "用户管理后台接口")
public class AdminUserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "后台添加用户")
    @PostMapping("/saveUser")
    public ResultVo<Object> saveUser(@RequestBody @Validated UserDto userDto, @ApiIgnore @UserInfo SelfUser userInfo) {
        userDto.setCreatorId(userInfo.getUserId());
        userDto.setCreatorName(userInfo.getUsername());
        userService.saveUser(userDto);
        return new ResultVo<>("操作成功:添加用户!");
    }

    @ApiOperation(value = "删除用户根据Id")
    @DeleteMapping("/deleteUser/{userId}")
    public ResultVo<Object> deleteUser(@PathVariable @Validated @ApiParam(value = "用户Id", required = true, example = "")
                                                   Long userId) {
        userService.deleteUserById(userId);
        return new ResultVo<>("操作提示: 删除用户!");
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/infoUser/{userId}")
    public ResultVo<UserVo> infoUser(@PathVariable Long userId) {
        UserVo userVo = userService.getUserById(userId);
        return new ResultVo<>("操作提示: 获取用户信息!", userVo);
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/updateUser")
    public ResultVo<Object> updateUser(@RequestBody UserDto userDto, @ApiIgnore @UserInfo SelfUser userInfo) {
        userDto.setUpdaterId(userInfo.getUserId());
        userDto.setUpdaterName(userInfo.getUsername());
        userService.updateUser(userDto);
        return new ResultVo<>("操作提示: 更新成功!");
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("/pageUser")
    public ResultVo<PageData<UserVo>> pageUser(@RequestBody PageData<UserVo> page) {
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

