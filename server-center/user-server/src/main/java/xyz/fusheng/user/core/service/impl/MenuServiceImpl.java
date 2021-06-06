package xyz.fusheng.user.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import xyz.fusheng.user.core.service.MenuService;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.user.core.mapper.MenuMapper;
import xyz.fusheng.core.model.entity.Menu;

import javax.annotation.Resource;

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
}
