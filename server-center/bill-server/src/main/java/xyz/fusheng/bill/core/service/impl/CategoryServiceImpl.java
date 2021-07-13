package xyz.fusheng.bill.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.bill.core.mapper.CategoryMapper;
import xyz.fusheng.bill.core.service.CategoryService;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.bill.dto.CategoryDto;
import xyz.fusheng.core.model.bill.entity.Category;
import xyz.fusheng.core.model.bill.vo.CategoryVo;

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
    public Page<CategoryVo> pageCategory(Page<CategoryVo> page) {
        List<CategoryVo> categoryVoList = categoryMapper.pageCategoryList(page);
        page.setList(categoryVoList);
        int totalCount = categoryMapper.countPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
