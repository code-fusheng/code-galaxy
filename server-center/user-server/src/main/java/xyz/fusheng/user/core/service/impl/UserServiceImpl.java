package xyz.fusheng.user.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import xyz.fusheng.user.core.mapper.UserMapper;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.user.core.service.UserService;

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
        return user;
    }

    @Override
    public User selectUserByPhone(String phone) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getPhone, phone));
//        if (ObjectUtils.isNotEmpty(user)) {
//            // 获取当前用户所有角色
//            List<Role> roleList = selectRolesByUserId(user.getUserId());
//            user.setRoleList(roleList);
//            // 获取当前用户所有权限
//            List<Menu> menuList = selectMenusByUserId(user.getUserId());
//            user.setMenuList(menuList);
//        }
        return user;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        List<Role> roleList = userMapper.selectRolesByUserId(userId);
        return roleList;
    }

    @Override
    public List<Menu> selectMenusByUserId(Long userId) {
        List<Menu> menuList = userMapper.selectMenusByUserId(userId);
        return menuList;
    }
}
