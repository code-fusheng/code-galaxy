package xyz.fusheng.user.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.core.model.dto.RoleDto;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.model.vo.RoleVo;
import xyz.fusheng.user.core.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: AdminRoleController
 * @Author: code-fusheng
 * @Date: 2021/7/12 下午2:40
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/admin/role")
@Api(tags = "角色管理后台接口", value = "角色管理后台接口")
public class AdminRoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/saveRole")
    public ResultVo<Object> saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
        return new ResultVo<>("操作提示: 角色添加成功!");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/deleteRole/{roleId}")
    public ResultVo<Object> deleteRole(@PathVariable Long roleId) {
        roleService.deleteRoleById(roleId);
        return new ResultVo<>("操作提示: 角色删除成功!");
    }

    @ApiOperation("获取角色详情")
    @GetMapping("/infoRole/{roleId}")
    public ResultVo<RoleVo> infoRole(@PathVariable Long roleId) {
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
    @GetMapping("/listRole")
    public ResultVo<List<Role>> listRole() {
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
