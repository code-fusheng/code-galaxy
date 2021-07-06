package xyz.fusheng.admin.article.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.admin.article.common.annotation.UserInfo;
import xyz.fusheng.admin.article.core.service.ArticleService;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.ArticleDto;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ArticleVo;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @FileName: ArticleController
 * @Author: code-fusheng
 * @Date: 2021/6/29 5:03 下午
 * @Version: 1.0
 * @Description: 文章控制类
 */

@Api(tags = "文章管理", value = "文章接口控制层")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("添加文章")
    @PostMapping("/saveArticle")
    public ResultVo<Object> saveArticle(@RequestBody ArticleDto articleDto,
                                        @ApiIgnore @UserInfo SelfUser userInfo) {
        articleDto.setAuthorId(userInfo.getUserId());
        articleDto.setCreatorId(userInfo.getUserId());
        articleDto.setCreatorName(userInfo.getUsername());
        articleService.saveArticle(articleDto);
        return new ResultVo<>("操作成功: 添加文章!");
    }

    @ApiOperation("分页查询文章")
    @PostMapping("/pageArticle")
    public ResultVo<Page<ArticleVo>> pageArticle(@RequestBody Page<ArticleVo> page) {
        page = articleService.pageArticle(page);
        return new ResultVo<>("操作成功: 分页查询分类!", page);
    }

    @ApiOperation("获取文章详情")
    @GetMapping("/getArticleById/{id}")
    public ResultVo<ArticleVo> getArticleById(@PathVariable Long id) {
        ArticleVo articleVo = articleService.getArticleById(id);
        return new ResultVo<>("操作成功: 获取文章详情!", articleVo);
    }

    @ApiOperation("更新文章")
    @PutMapping("/updateArticle")
    public ResultVo<Object> updateArticle(@RequestBody ArticleDto articleDto,
                                          @ApiIgnore @UserInfo SelfUser userInfo) {
        articleDto.setUpdaterId(userInfo.getUserId());
        articleDto.setUpdaterName(userInfo.getUsername());
        articleService.updateArticle(articleDto);
        return new ResultVo<>("操作成功: 更新文章!");
    }

    @ApiOperation("批量删除文章")
    @DeleteMapping("/deleteArticleByIds")
    public ResultVo<Object> batchDeleteArticleByIds(@RequestBody Long[] ids) {
        articleService.batchDeleteArticleByIds(ids);
        return new ResultVo<>("操作成功: 批量删除文章!");
    }

}
