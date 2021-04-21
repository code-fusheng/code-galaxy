package xyz.fusheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.mapper.MenuMapper;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.service.MenuService;

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
