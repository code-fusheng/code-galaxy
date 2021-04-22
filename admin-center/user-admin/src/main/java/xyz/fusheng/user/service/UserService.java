package xyz.fusheng.user.service;

import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.entity.User;

public interface UserService{

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    boolean saveUser(UserDto userDto);

    /**
     * 删除用户根据id
     * @param userId
     */
    void deleteUserById(Long userId);
}
