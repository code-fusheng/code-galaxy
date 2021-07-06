package xyz.fusheng.admin.article.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.entity.Article;
import xyz.fusheng.core.model.vo.ArticleVo;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleVo> pageArticle(Page<ArticleVo> page);

    int countArticleByPage(Page<ArticleVo> page);

    ArticleVo getArticleById(Long id);
}
