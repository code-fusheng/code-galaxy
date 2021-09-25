package xyz.fusheng.article.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.article.core.mapper.ArticleMapper;
import xyz.fusheng.article.core.mapper.CategoryMapper;
import xyz.fusheng.article.core.service.CategoryService;
import xyz.fusheng.article.model.dto.CategoryDto;
import xyz.fusheng.article.model.entity.Article;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.article.model.vo.CategoryVo;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.PageData;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category oldCategory = categoryMapper.selectOne(new QueryWrapper<Category>().lambda()
                .eq(Category::getCategoryName, categoryDto.getCategoryName())
                .eq(Category::getIsEnabled, StateEnums.ENABLED.getCode()));
        if (ObjectUtils.isNotEmpty(oldCategory)) {
            throw new BusinessException(ResultEnum.BUSINESS_ERROR.getCode(), "分类「" + categoryDto.getCategoryName() + "」已经存在!");
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        categoryMapper.insert(category);
        log.info("插入分类数据结果:{}", category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Integer selectCount = articleMapper.selectCount(new QueryWrapper<Article>().lambda()
                .eq(Article::getIsEnabled, StateEnums.ENABLED.getCode())
                .eq(Article::getArticleCategory, id));
        if (selectCount > 0) {
            throw new BusinessException(ResultEnum.BUSINESS_ERROR.getCode(), "当前分类存在文章!");
        }
        int deleteResult = categoryMapper.deleteById(id);
        log.info("分类删除结果:{}", deleteResult);
    }

    @Override
    public CategoryVo getCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

    @Override
    public PageData<CategoryVo> getCategoryByPage(PageData<CategoryVo> page) {
        List<CategoryVo> categoryVoList = categoryMapper.getCategoryByPage(page);
        page.setList(categoryVoList);
        int totalCount = categoryMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Category> getAllCategoryList() {
        List<Category> categoryList = categoryMapper.selectList(new QueryWrapper<Category>().lambda()
                .eq(Category::getIsEnabled, StateEnums.ENABLED.getCode()));
        return categoryList;
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        int updateResult = categoryMapper.updateById(category);
        log.info("分类更新结果:{}", updateResult);
    }

    @Override
    public List<Category> getSimpleCategoryList() {
        List<Category> categoryList = categoryMapper.selectList(new QueryWrapper<Category>().lambda()
                .select(Category::getCategoryId, Category::getCategoryName, Category::getCategoryImage)
                .eq(Category::getIsEnabled, StateEnums.ENABLED.getCode()).orderByDesc(Category::getArticleCount));
        return categoryList;
    }
}

