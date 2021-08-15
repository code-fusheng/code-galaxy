package xyz.fusheng.article.core.service;

import xyz.fusheng.article.model.dto.CategoryDto;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.article.model.vo.CategoryVo;
import xyz.fusheng.core.model.base.PageData;

import java.util.List;

public interface CategoryService {

    void saveCategory(CategoryDto categoryDto);

    void deleteCategoryById(Long id);

    CategoryVo getCategoryById(Long id);

    PageData<CategoryVo> getCategoryByPage(PageData<CategoryVo> page);

    List<Category> getAllCategoryList();

    void updateCategory(CategoryDto categoryDto);

    List<Category> getSimpleCategoryList();
}


