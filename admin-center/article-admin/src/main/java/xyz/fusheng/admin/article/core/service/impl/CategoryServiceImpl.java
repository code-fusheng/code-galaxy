package xyz.fusheng.admin.article.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.admin.article.core.mapper.CategoryMapper;
import xyz.fusheng.admin.article.core.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

}

