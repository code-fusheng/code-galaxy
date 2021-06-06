package xyz.fusheng.admin.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.UserVo;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页获取用户列表
     * @param page
     * @return
     */
    List<UserVo> getUserByPage(Page<UserVo> page);

    /**
     * 获取分页总数
     * @param page
     * @return
     */
    int getUserCountByPage(Page<UserVo> page);
}
