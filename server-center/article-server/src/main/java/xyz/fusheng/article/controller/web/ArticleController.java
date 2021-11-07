package xyz.fusheng.article.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.article.core.service.ArticleService;
import xyz.fusheng.article.model.dto.ArticleDto;
import xyz.fusheng.article.model.vo.ArticleVo;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: ArticleController
 * @Author: code-fusheng
 * @Date: 2021/8/13 上午12:48
 * @Version: 1.0
 * @Description: 文章接口 Web端
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("保存草稿")
    @PostMapping("/saveDraft")
    public ResultVo<Object>  saveDraft(@RequestBody ArticleDto articleDto) {
        boolean result = articleService.saveDraft(articleDto);
        return result ? ResultVo.success() : ResultVo.error("保存草稿失败!");
    }

    @ApiOperation("保存发布")
    @PostMapping("/savePublish")
    public ResultVo<Object> savePublish(@RequestBody ArticleDto articleDto) {
        boolean result = articleService.savePublish(articleDto);
        return result ? ResultVo.success() : ResultVo.error("内容发布失败!");
    }

    @ApiOperation("文章列表")
    @PostMapping("/pageList")
    public ResultVo<PageData<ArticleVo>> pageList(@RequestBody PageData<ArticleVo> page) {
        page = articleService.pageList(page);
        return ResultVo.success(page);
    }

    @ApiOperation("阅读详情")
    @GetMapping("/readInfo/{id}")
    public ResultVo<ArticleVo> readInfo(@PathVariable Long id) {
        ArticleVo articleVo = articleService.readInfo(id);
        return ResultVo.success(articleVo);
    }

    @ApiOperation("上下篇")
    @GetMapping("/lastAndNext/{id}")
    public ResultVo<List<ArticleVo>> lastAndNext(@PathVariable("id") Long id) {
        List<ArticleVo> articleVoList = articleService.lastAndNext(id);
        return ResultVo.success(articleVoList);
    }




}
