package xyz.fusheng.admin.user.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.vo.MenuVo;

import java.util.List;

public interface MenuService {

    Menu getMenuByPath(String path);

    /**
     * 获取可用权限列表
     * @return
     */
    List<Menu> getMenuList();

    /**
     * 角色的权限Ids
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 分页查询权限列表
     * @param page
     * @return
     */
    Page<MenuVo> getMenuByPage(Page<MenuVo> page);
}

