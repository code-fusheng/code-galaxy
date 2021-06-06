package xyz.fusheng.admin.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Long> getRoleIdsByUserId(Long userId);

}
