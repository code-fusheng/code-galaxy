package xyz.fusheng.article.controller.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.article.core.service.CategoryService;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: CategoryController
 * @Author: code-fusheng
 * @Date: 2021/8/12 下午11:20
 * @Version: 1.0
 * @Description: 类目接口 Web端
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("获取可用分类列表")
    @GetMapping("/listAll")
    public ResultVo<List<Category>> listAll() {
        List<Category> categoryList = categoryService.getSimpleCategoryList();
        return ResultVo.success(categoryList);
    }

}
