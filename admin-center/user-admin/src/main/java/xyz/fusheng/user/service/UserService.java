package xyz.fusheng.user.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.UserDto;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.RoleVo;
import xyz.fusheng.model.vo.UserVo;

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

    /**
     * 分页查询用户列表
     * @param page
     * @return
     */
    Page<UserVo> getUserByPage(Page<UserVo> page);
}
