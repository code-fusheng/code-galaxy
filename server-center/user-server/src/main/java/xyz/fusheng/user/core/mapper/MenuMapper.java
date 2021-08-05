package xyz.fusheng.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.vo.MenuVo;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> getMenuIdsByRoleId(Long roleId);

    List<MenuVo> getMenuByPage(PageData<MenuVo> page);

    int getMenuCountByPage(PageData<MenuVo> page);

}
