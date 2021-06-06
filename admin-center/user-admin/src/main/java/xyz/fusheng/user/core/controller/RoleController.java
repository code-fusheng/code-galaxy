package xyz.fusheng.user.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.model.dto.RoleDto;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.model.vo.RoleVo;
import xyz.fusheng.user.core.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: RoleController
 * @Author: code-fusheng
 * @Date: 2021/5/18 3:12 下午
 * @Version: 1.0
 * @Description: 角色控制类
 */

@RestController
@RequestMapping("/role")
@Api(tags = "角色管理后台接口", value = "角色管理后台接口")
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/saveRole")
    public ResultVo<Object> saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
        return new ResultVo<>("操作提示: 角色添加成功!");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/deleteRoleById/{roleId}")
    public ResultVo<Object> deleteRoleById(@PathVariable Long roleId) {
        roleService.deleteRoleById(roleId);
        return new ResultVo<>("操作提示: 角色删除成功!");
    }

    @ApiOperation("获取角色详情")
    @GetMapping("/getRuleById/{roleId}")
    public ResultVo<RoleVo> getRuleById(@PathVariable Long roleId) {
        RoleVo roleVo = roleService.getRoleById(roleId);
        return new ResultVo<>("操作成功：获取角色详情!", roleVo);
    }

    @ApiOperation("修改角色信息")
    @PutMapping("/updateRole")
    public ResultVo<Object> updateRole(@RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto);
        return new ResultVo<>("操作成功: 更新角色!");
    }

    @ApiOperation("查询角色列表")
    @GetMapping("/getRoleList")
    public ResultVo<List<Role>> getRoleList() {
        List<Role> roleList = roleService.getRoleList();
        return new ResultVo<>("操作成功: 查询角色列表!", roleList);
    }

    @ApiOperation("通过用户Id获取角色Ids")
    @GetMapping("/getRoleIdsByUserId/{userId}")
    public ResultVo<List<Long>> getRoleIdsByUserId(@PathVariable Long userId) {
        List<Long> ids = roleService.getRoleIdsByUserId(userId);
        return new ResultVo<>("操作成功: 查询用户拥有的角色Ids", ids);
    }
}
