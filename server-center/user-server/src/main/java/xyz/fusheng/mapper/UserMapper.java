package xyz.fusheng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.entity.Role;
import xyz.fusheng.model.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> selectRolesByUserId(Long userId);
}