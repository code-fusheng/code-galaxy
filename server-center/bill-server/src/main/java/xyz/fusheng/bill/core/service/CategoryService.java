package xyz.fusheng.bill.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.bill.dto.CategoryDto;
import xyz.fusheng.core.model.bill.vo.CategoryVo;

public interface CategoryService{

    void saveCategory(CategoryDto categoryDto);

    Page<CategoryVo> pageCategory(Page<CategoryVo> page);
}
