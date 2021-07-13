package xyz.fusheng.user.core.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.dto.RoleDto;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.vo.RoleVo;
import xyz.fusheng.user.core.mapper.RoleMapper;
import xyz.fusheng.user.core.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void saveRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        roleMapper.insert(role);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleMapper.deleteById(roleId);
    }

    /**
     * 获取角色详情
     *
     * @param roleId
     * @return
     */
    @Override
    public RoleVo getRoleById(Long roleId) {
        RoleVo roleVo = new RoleVo();
        Role role = roleMapper.selectById(roleId);
        if (ObjectUtils.isEmpty(role)) {
            throw new BusinessException(ResultEnums.BUSINESS_ERROR.getCode(), "角色信息查询异常!");
        }
        BeanUtils.copyProperties(role, roleVo);
        return roleVo;
    }

    /**
     * 更新角色
     *
     * @param roleDto
     */
    @Override
    public void updateRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        roleMapper.updateById(role);
    }

    /**
     * 查询所有角色列表
     *
     * @return
     */
    @Override
    public List<Role> getRoleList() {
        return roleMapper.selectList(null);
    }

    /**
     * 通过用户获取角色Ids
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        List<Long> ids = roleMapper.getRoleIdsByUserId(userId);
        return ids;
    }

}
