package xyz.fusheng.bill.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.bill.model.entity.Category;
import xyz.fusheng.bill.model.vo.CategoryVo;
import xyz.fusheng.core.model.base.PageData;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> pageCategoryList(PageData<CategoryVo> page);

    int countPage(PageData<CategoryVo> page);
}
