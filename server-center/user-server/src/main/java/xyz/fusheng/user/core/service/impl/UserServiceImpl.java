package xyz.fusheng.user.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.UserDto;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.UserVo;
import xyz.fusheng.user.core.mapper.UserMapper;
import xyz.fusheng.user.core.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Override
    public boolean saveUser(UserDto userDto) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userDto.getUsername())
                .eq(User::getIsEnabled, StateEnums.ENABLED.getCode()));
        if (ObjectUtils.isNotEmpty(user)) {
            throw new BusinessException("添加失败:该用户名已经存在!");
        }
        user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPhone()));
        int insertResult = userMapper.insert(user);
        return 1 == insertResult;
    }

    @Override
    public void deleteUserById(Long userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public Page<UserVo> getUserByPage(Page<UserVo> page) {
        List<UserVo> userVoList = userMapper.getUserByPage(page);
        page.setList(userVoList);
        int totalCount = userMapper.getUserCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#userId")
    public UserVo getUserById(Long userId) {
        UserVo userVo = new UserVo();
        User user = userMapper.selectById(userId);
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userMapper.updateById(user);
    }
}
