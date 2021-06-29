package xyz.fusheng.admin.article.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.entity.Article;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
