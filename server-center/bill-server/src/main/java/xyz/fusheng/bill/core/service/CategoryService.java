package xyz.fusheng.bill.core.service;

import xyz.fusheng.bill.model.dto.CategoryDto;
import xyz.fusheng.bill.model.vo.CategoryVo;
import xyz.fusheng.core.model.base.Page;

public interface CategoryService{

    void saveCategory(CategoryDto categoryDto);

    Page<CategoryVo> pageCategory(Page<CategoryVo> page);
}
