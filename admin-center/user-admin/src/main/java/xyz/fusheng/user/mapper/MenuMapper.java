package xyz.fusheng.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.vo.MenuVo;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> getMenuIdsByRoleId(Long roleId);
    
    List<MenuVo> getMenuByPage(Page<MenuVo> page);

    int getMenuCountByPage(Page<MenuVo> page);
}
