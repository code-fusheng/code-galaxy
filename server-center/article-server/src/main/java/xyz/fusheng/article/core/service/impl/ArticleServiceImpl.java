package xyz.fusheng.article.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.fusheng.article.core.mapper.ArticleMapper;
import xyz.fusheng.article.core.mapper.CategoryMapper;
import xyz.fusheng.article.core.service.ArticleService;
import xyz.fusheng.article.model.dto.ArticleDto;
import xyz.fusheng.article.model.entity.Article;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.article.model.vo.ArticleVo;
import xyz.fusheng.core.constants.GlobalConstants;
import xyz.fusheng.core.enums.ArticleStateEnum;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.utils.SecurityUtils;
import xyz.fusheng.core.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    // Web用户端

    /**
     * 保存草稿
     * @param articleDto
     * @return
     */
    @Override
    public boolean saveDraft(ArticleDto articleDto) {
        SelfUser userInfo = SecurityUtils.getUserInfo();
        articleDto.setState(ArticleStateEnum.DRAFT);
        // 草稿内容默认私有
        articleDto.setIsPublish(GlobalConstants.NO);
        articleDto.setCreatorId(userInfo.getUserId());
        articleDto.setCreatorName(userInfo.getUsername());
        articleDto.setAuthorId(userInfo.getUserId());
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        return SqlHelper.retBool(articleMapper.insert(article));
    }

    @Override
    public boolean savePublish(ArticleDto articleDto) {
        return false;
    }

    @Override
    public PageData<ArticleVo> pageList(PageData<ArticleVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 文章标题
            String[] sortColumns = {"article_title", "author_name", "good_count", "read_count", "collection_count", "comment_count", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                throw new BusinessException(ResultEnum.ERROR.getCode(), "参数错误!");
            }
        }
        // 获取数据
        List<ArticleVo> articleVoList = articleMapper.pageArticle(page);
        page.setList(articleVoList);
        // 统计总数
        int totalCount = articleMapper.countArticleByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public ArticleVo readInfo(Long id) {
        ArticleVo articleVo = new ArticleVo();
        Article article = articleMapper.selectById(id);
        article.setReadCount(article.getReadCount() + 1);
        BeanUtils.copyProperties(article, articleVo);
        articleMapper.updateById(article);
        return articleVo;
    }

    // Admin管理后台

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleMapper.insert(article);
        log.info("[文章管理-插入文章数据] => article:{}", article);
        // 更新分类文章数
        Category category = categoryMapper.selectById(articleDto.getArticleCategory());
        if (ObjectUtils.isEmpty(category)) { throw new BusinessException(ResultEnum.BUSINESS_ERROR.getCode(), "文章分类编号对应数据不存在!");  }
        Integer oldCount = category.getArticleCount();
        Integer count = articleMapper.selectCount(new QueryWrapper<Article>().lambda()
                .eq(Article::getArticleCategory, articleDto.getArticleCategory())
                .eq(Article::getIsEnabled, GlobalConstants.YES));
        category.setArticleCount(count);
        categoryMapper.updateById(category);
        log.info("[文章管理-更新分类文章数] => oldCount:{} -> newCount:{}",oldCount, count );
    }

    @Override
    public PageData<ArticleVo> pageArticle(PageData<ArticleVo> page) {
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 文章分类、创建时间、更新时间
            String[] sortColumns = {"article_category", "created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(page.getSortColumn().toLowerCase())) {
                throw new BusinessException(ResultEnum.BUSINESS_ERROR.getCode(), "参数错误!");
            }
        }
        List<ArticleVo> articleVoList = articleMapper.pageArticle(page);
        page.setList(articleVoList);
        int totalCount = articleMapper.countArticleByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public ArticleVo getArticleById(Long id) {
        ArticleVo articleVo = articleMapper.getArticleById(id);
        return articleVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleDto articleDto) {
        // 获取article的旧/新分类
        long oldCategoryId = articleMapper.selectById(articleDto.getArticleId()).getArticleCategory();
        long newCategoryId = articleDto.getArticleCategory();
        // 判断是否更改了分类
        if(oldCategoryId != newCategoryId) {
            // 更新 旧分类 文章数
            Category oldCategory = categoryMapper.selectById(oldCategoryId);
            oldCategory.setArticleCount(oldCategory.getArticleCount() - 1);
            categoryMapper.updateById(oldCategory);
            // 更新 新分类 文章数
            Category newCategory = categoryMapper.selectById(newCategoryId);
            newCategory.setArticleCount(newCategory.getArticleCount() + 1);
            categoryMapper.updateById(newCategory);
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleMapper.updateById(article);
        log.info("[文章管理-更新文章] => article:{}", article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteArticleByIds(Long[] ids) {
        List<Long> articleIds = Arrays.asList(ids);
        for (Long id : articleIds) {
            Article article = articleMapper.selectById(id);
            if (ObjectUtils.isEmpty(article)) { continue; }
            Category category = categoryMapper.selectById(article.getArticleCategory());
            Integer oldCount = category.getArticleCount();
            int deleteResult = articleMapper.deleteById(id);
            if (deleteResult == 0) { continue; }
            log.info("文章删除结果:{}", deleteResult);
            Integer count = articleMapper.selectCount(new QueryWrapper<Article>().lambda()
                    .eq(article.getArticleCategory() == null, Article::getArticleCategory, article.getArticleCategory())
                    .eq(Article::getIsEnabled, StateEnums.ENABLED.getCode()));
            category.setArticleCount(count);
            categoryMapper.updateById(category);
            log.info("更新分类文章数结果:{} -> {}",oldCount, count);
        }
    }

}

