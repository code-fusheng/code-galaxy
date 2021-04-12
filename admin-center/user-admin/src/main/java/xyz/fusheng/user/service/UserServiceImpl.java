package xyz.fusheng.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.util.Assert;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.user.mapper.UserMapper;
import xyz.fusheng.user.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean saveUser(UserDto userDto) {

        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userDto.getUsername())
                .eq(User::getIsEnabled, StateEnums.ENABLED.getCode()));
        Assert.isTrue(ObjectUtils.isEmpty(user), "添加失败:该用户名已经存在!");
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPhone()));
        int insertResult = userMapper.insert(user);
        return 1 == insertResult;
    }
}
