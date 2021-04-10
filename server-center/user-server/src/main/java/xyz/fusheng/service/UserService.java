package xyz.fusheng.service;


import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.entity.User;

import java.util.List;

public interface UserService {

    User selectUserByUsername(String username);

    User selectUserByPhone(String phone);

    List<Role> selectRolesByUserId(Long userId);

    List<Menu> selectMenusByUserId(Long userId);
}

