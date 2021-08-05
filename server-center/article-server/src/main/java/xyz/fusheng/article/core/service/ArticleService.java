package xyz.fusheng.article.core.service;

import xyz.fusheng.article.model.dto.ArticleDto;
import xyz.fusheng.article.model.vo.ArticleVo;
import xyz.fusheng.core.model.base.Page;

public interface ArticleService {

    void saveArticle(ArticleDto articleDto);

    Page<ArticleVo> pageArticle(Page<ArticleVo> page);

    ArticleVo getArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void batchDeleteArticleByIds(Long[] ids);
}

