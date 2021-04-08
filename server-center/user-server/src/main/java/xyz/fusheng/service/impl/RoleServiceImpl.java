package xyz.fusheng.service.impl;

import org.springframework.stereotype.Service;

import xyz.fusheng.mapper.RoleMapper;
import xyz.fusheng.service.RoleService;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

}
