package xyz.fusheng.user.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.vo.MenuVo;

import java.util.List;

public interface MenuService {

    /**
     * 根据path获取权限信息
     *
     * @param path
     * @return
     */
    Menu getMenuByPath(String path);

    /**
     * 获取可用权限列表
     *
     * @return
     */
    List<Menu> getMenuList();

    /**
     * 角色的权限Ids
     *
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 分页查询权限列表
     *
     * @param page
     * @return
     */
    PageData<MenuVo> getMenuByPage(PageData<MenuVo> page);

}

