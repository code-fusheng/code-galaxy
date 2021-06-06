package xyz.fusheng.user.core.service;


import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;

import java.util.List;

public interface UserService {

    User selectUserByUsername(String username);

    User selectUserByPhone(String phone);

    List<Role> selectRolesByUserId(Long userId);

    List<Menu> selectMenusByUserId(Long userId);
}

