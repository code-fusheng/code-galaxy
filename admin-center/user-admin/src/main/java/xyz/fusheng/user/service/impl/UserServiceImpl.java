package xyz.fusheng.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.util.Assert;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.exception.BusinessException;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.UserVo;
import xyz.fusheng.user.mapper.UserMapper;
import xyz.fusheng.user.service.UserService;

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
        User user = userMapper.selectById(userId);
        user.setIsDeleted(StateEnums.DELETED.getCode());
        userMapper.updateById(user);
        logger.info("删除用户信息:{}", user);
    }

    @Override
    public Page<UserVo> getUserByPage(Page<UserVo> page) {
        List<UserVo> userVoList = userMapper.getUserByPage(page);
        page.setList(userVoList);
        int totalCount = userMapper.getUserCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
