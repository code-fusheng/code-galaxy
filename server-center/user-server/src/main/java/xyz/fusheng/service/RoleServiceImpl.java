package xyz.fusheng.service;

import org.springframework.stereotype.Service;

import xyz.fusheng.mapper.RoleMapper;
import xyz.fusheng.service.impl.RoleService;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

}
