package xyz.fusheng.article.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.CategoryDto;
import xyz.fusheng.core.model.entity.Category;
import xyz.fusheng.core.model.vo.CategoryVo;

import java.util.List;

public interface CategoryService {

    void saveCategory(CategoryDto categoryDto);

    void deleteCategoryById(Long id);

    CategoryVo getCategoryById(Long id);

    Page<CategoryVo> getCategoryByPage(Page<CategoryVo> page);

    List<Category> getAllCategoryList();

    void updateCategory(CategoryDto categoryDto);
}


