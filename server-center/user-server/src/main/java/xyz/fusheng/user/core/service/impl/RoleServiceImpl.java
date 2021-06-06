package xyz.fusheng.user.core.service.impl;

import org.springframework.stereotype.Service;

import xyz.fusheng.user.core.mapper.RoleMapper;
import xyz.fusheng.user.core.service.RoleService;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

}
