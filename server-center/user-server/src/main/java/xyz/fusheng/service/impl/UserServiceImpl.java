package xyz.fusheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.mapper.UserMapper;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectUserByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getUsername, username));
        if (ObjectUtils.isNotEmpty(user)) {
            List<Role> roleList = selectRolesByUserId(user.getUserId());
            user.setRoleList(roleList);
        }
        return user;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        List<Role> roleList = userMapper.selectRolesByUserId(userId);
        return roleList;
    }
}
