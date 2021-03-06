package xyz.fusheng.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.Role;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.UserVo;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> selectRolesByUserId(Long userId);

    List<Menu> selectMenusByUserId(Long userId);

    /**
     * 分页获取用户列表
     *
     * @param page
     * @return
     */
    List<UserVo> getUserByPage(PageData<UserVo> page);

    /**
     * 获取分页总数
     *
     * @param page
     * @return
     */
    int getUserCountByPage(PageData<UserVo> page);
}
