package xyz.fusheng.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}