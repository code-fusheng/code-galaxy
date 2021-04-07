package xyz.fusheng.service;

import org.springframework.stereotype.Service;

import xyz.fusheng.mapper.UserMapper;
import xyz.fusheng.service.impl.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

}
