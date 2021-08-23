package xyz.fusheng.article.controller.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.article.core.service.ArticleService;
import xyz.fusheng.article.model.dto.ArticleDto;
import xyz.fusheng.article.model.vo.ArticleVo;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;

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
    public ResultVo  saveDraft(@RequestBody ArticleDto articleDto) {
        boolean result = articleService.saveDraft(articleDto);
        return result ? ResultVo.success() : ResultVo.error("保存草稿失败!");
    }

    @ApiOperation("阅读文章")
    @GetMapping("/read/{id}")
    public ResultVo<ArticleVo> read(@PathVariable("id") Long id) {

        return new ResultVo<>();
    }

}
