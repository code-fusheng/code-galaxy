package xyz.fusheng.article.core.service;

import xyz.fusheng.article.model.dto.CategoryDto;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.article.model.vo.CategoryVo;
import xyz.fusheng.core.model.base.Page;

import java.util.List;

public interface CategoryService {

    void saveCategory(CategoryDto categoryDto);

    void deleteCategoryById(Long id);

    CategoryVo getCategoryById(Long id);

    Page<CategoryVo> getCategoryByPage(Page<CategoryVo> page);

    List<Category> getAllCategoryList();

    void updateCategory(CategoryDto categoryDto);
}


