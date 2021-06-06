package xyz.fusheng.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> selectRolesByUserId(Long userId);

    List<Menu> selectMenusByUserId(Long userId);
}
