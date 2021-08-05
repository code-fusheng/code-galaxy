package xyz.fusheng.article.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.article.model.vo.CategoryVo;
import xyz.fusheng.core.model.base.Page;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> getCategoryByPage(Page<CategoryVo> page);

    int getCountByPage(Page<CategoryVo> page);
}
