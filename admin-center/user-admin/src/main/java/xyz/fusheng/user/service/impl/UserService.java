package xyz.fusheng.user.service.impl;

import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.entity.User;

public interface UserService{

    boolean saveUser(UserDto userDto);

}
