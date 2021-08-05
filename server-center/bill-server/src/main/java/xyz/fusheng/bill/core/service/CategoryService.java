package xyz.fusheng.bill.core.service;

import xyz.fusheng.bill.model.dto.CategoryDto;
import xyz.fusheng.bill.model.vo.CategoryVo;
import xyz.fusheng.core.model.base.PageData;

public interface CategoryService{

    void saveCategory(CategoryDto categoryDto);

    PageData<CategoryVo> pageCategory(PageData<CategoryVo> page);
}
