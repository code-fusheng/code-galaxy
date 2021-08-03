package xyz.fusheng.user.core.service;


import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.UserDto;
import xyz.fusheng.core.model.dto.UserRegisterDto;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.UserVo;

import java.util.List;

public interface UserService {

    User selectUserByPhone(String phone);

    List<Role> selectRolesByUserId(Long userId);

    List<Menu> selectMenusByUserId(Long userId);

    /**
     * 通过用户名获取用户信息
     */
    User selectUserByUsername(String username);

    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    boolean saveUser(UserDto userDto);

    /**
     * 删除用户根据id
     *
     * @param userId
     */
    void deleteUserById(Long userId);

    /**
     * 分页查询用户列表
     *
     * @param page
     * @return
     */
    Page<UserVo> getUserByPage(Page<UserVo> page);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserVo getUserById(Long userId);

    /**
     * 修改用户
     *
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * 注册用户
     *
     * @param userRegisterDto
     */
    void registerUser(UserRegisterDto userRegisterDto);
}

