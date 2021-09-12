package xyz.fusheng.article.core.service;

import xyz.fusheng.article.model.dto.ArticleDto;
import xyz.fusheng.article.model.vo.ArticleVo;
import xyz.fusheng.core.model.base.PageData;

public interface ArticleService {

    void saveArticle(ArticleDto articleDto);

    PageData<ArticleVo> pageArticle(PageData<ArticleVo> page);

    ArticleVo getArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void batchDeleteArticleByIds(Long[] ids);

    /**
     * 保存草稿
     * @param articleDto
     */
    boolean saveDraft(ArticleDto articleDto);

    /**
     * 发布文章
     * @param articleDto
     * @return
     */
    boolean savePublish(ArticleDto articleDto);

    /**
     * 文章分页列表
     * @param page
     * @return
     */
    PageData<ArticleVo> pageList(PageData<ArticleVo> page);

    /**
     * 阅读详情
     * @param id
     * @return
     */
    ArticleVo readInfo(Long id);
}

