package xyz.fusheng.core.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.entity.Article;

/**
 * @FileName: ArticleVo
 * @Author: code-fusheng
 * @Date: 2021/6/29 4:57 下午
 * @Version: 1.0
 * @Description: 文章实体
 */

@Data
@ApiModel("文章视图对象")
public class ArticleVo extends Article {

    @ApiModelProperty("分类名称")
    private String categoryName;

}
