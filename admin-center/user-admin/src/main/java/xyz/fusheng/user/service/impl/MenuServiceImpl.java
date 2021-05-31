package xyz.fusheng.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.vo.MenuVo;
import xyz.fusheng.user.mapper.MenuMapper;
import xyz.fusheng.user.service.MenuService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Menu getMenuByPath(String path) {
        Menu menu = menuMapper.selectOne(new QueryWrapper<Menu>().lambda()
                .eq(Menu::getPath, path)
                .eq(Menu::getIsEnabled, StateEnums.ENABLED.getCode()));
        return menu;
    }

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().lambda()
                .eq(Menu::getIsEnabled, StateEnums.ENABLED.getCode()));
        return menuList;
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        List<Long> menuIds = menuMapper.getMenuIdsByRoleId(roleId);
        return menuIds;
    }

    /**
     * 分页查询权限列表
     * @param page
     * @return
     */
    @Override
    public Page<MenuVo> getMenuByPage(Page<MenuVo> page) {
        List<MenuVo> menuVoList = menuMapper.getMenuByPage(page);
        page.setList(menuVoList);
        int totalCount = menuMapper.getMenuCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
