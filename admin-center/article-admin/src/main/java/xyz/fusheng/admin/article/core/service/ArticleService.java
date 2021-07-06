package xyz.fusheng.admin.article.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.ArticleDto;
import xyz.fusheng.core.model.vo.ArticleVo;

public interface ArticleService {

    void saveArticle(ArticleDto articleDto);

    Page<ArticleVo> pageArticle(Page<ArticleVo> page);

    ArticleVo getArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void batchDeleteArticleByIds(Long[] ids);
}

