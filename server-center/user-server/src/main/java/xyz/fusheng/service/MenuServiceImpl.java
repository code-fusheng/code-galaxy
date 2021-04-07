package xyz.fusheng.service;

import org.springframework.stereotype.Service;

import xyz.fusheng.mapper.MenuMapper;
import xyz.fusheng.service.impl.MenuService;

import javax.annotation.Resource;

@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuMapper menuMapper;

}
