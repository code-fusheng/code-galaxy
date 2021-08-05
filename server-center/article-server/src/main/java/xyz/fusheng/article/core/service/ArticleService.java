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
}

