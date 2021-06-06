package xyz.fusheng.user.core.service;

import xyz.fusheng.model.dto.RoleDto;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.vo.RoleVo;

import java.util.List;

public interface RoleService{

    /**
     * 添加角色
     * @param roleDto
     */
    void saveRole(RoleDto roleDto);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRoleById(Long roleId);

    /**
     * 获取角色详情
     * @param roleId
     * @return
     */
    RoleVo getRoleById(Long roleId);

    /**
     * 修改角色
     * @param roleDto
     */
    void updateRole(RoleDto roleDto);

    /**
     * 查询所有角色列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 获取用户角色Ids
     * @param userId
     * @return
     */
    List<Long> getRoleIdsByUserId(Long userId);
}
