package xyz.fusheng.bill.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.bill.core.mapper.CategoryMapper;
import xyz.fusheng.bill.core.service.CategoryService;
import xyz.fusheng.bill.model.dto.CategoryDto;
import xyz.fusheng.bill.model.entity.Category;
import xyz.fusheng.bill.model.vo.CategoryVo;
import xyz.fusheng.core.constants.GlobalConstants;
import xyz.fusheng.core.model.base.PageData;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        categoryMapper.insert(category);
    }

    @Override
    public PageData<Category> pageCategory(PageData<Category> page) {
        IPage<Category> categoryIPage = categoryMapper.selectPage(page.getPage(), new QueryWrapper<Category>().lambda()
                .eq(Category::getIsEnabled, GlobalConstants.YES));
        PageData<Category> pageData = new PageData<>(categoryIPage);
        return pageData;
    }

}
