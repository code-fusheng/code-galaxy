package xyz.fusheng.article.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.article.model.entity.Article;
import xyz.fusheng.article.model.vo.ArticleVo;
import xyz.fusheng.core.model.base.PageData;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleVo> pageArticle(PageData<ArticleVo> page);

    int countArticleByPage(PageData<ArticleVo> page);

    ArticleVo getArticleById(Long id);

    ArticleVo getLastArticle(Long id, Long articleCategory);

    ArticleVo getNextArticle(Long id, Long articleCategory);
}
