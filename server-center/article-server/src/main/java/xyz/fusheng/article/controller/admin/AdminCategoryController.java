package xyz.fusheng.article.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.article.common.annotation.UserInfo;
import xyz.fusheng.article.core.service.CategoryService;
import xyz.fusheng.article.model.dto.CategoryDto;
import xyz.fusheng.article.model.entity.Category;
import xyz.fusheng.article.model.vo.CategoryVo;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: CategoryController
 * @Author: code-fusheng
 * @Date: 2021/6/29 5:06 下午
 * @Version: 1.0
 * @Description: 分类控制类
 */

@Api(tags = "分类管理", value = "分类接口控制")
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("添加分类")
    @PostMapping("/saveCategory")
    public ResultVo<Object> saveCategory(@RequestBody CategoryDto categoryDto,
                                         @ApiIgnore @UserInfo SelfUser userInfo) {
        categoryDto.setCreatorId(userInfo.getUserId());
        categoryDto.setCreatorName(userInfo.getUsername());
        categoryService.saveCategory(categoryDto);
        return new ResultVo<>("操作成功: 添加分类!");
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/deleteCategory/{id}")
    public ResultVo<Object> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return new ResultVo<>("操作成功: 删除分类!");
    }

    @ApiOperation("查询分类详情")
    @GetMapping("/infoCategory/{id}")
    public ResultVo<CategoryVo> infoCategory(@PathVariable("id") Long id) {
        CategoryVo categoryVo = categoryService.getCategoryById(id);
        return new ResultVo<>("操作成功: 查询分类!", categoryVo);
    }

    @ApiOperation("修改分类")
    @PutMapping("/updateCategory")
    public ResultVo<Object> updateCategory(@RequestBody CategoryDto categoryDto, @ApiIgnore @UserInfo SelfUser userInfo) {
        categoryDto.setCreatorId(userInfo.getUserId());
        categoryDto.setCreatorName(userInfo.getUsername());
        categoryService.updateCategory(categoryDto);
        return new ResultVo<>("操作成功: 修改分类!");
    }

    @ApiOperation("多条件分页查询分类")
    @PostMapping("/pageCategory")
    public ResultVo<PageData<CategoryVo>> pageCategory(@RequestBody PageData<CategoryVo> page) {
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 创建时间、更新时间
            String[] sortColumns = {"created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(page.getSortColumn().toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = categoryService.getCategoryByPage(page);
        return new ResultVo<>("操作成功: 分页查询分类!", page);
    }

    @ApiOperation("查询可用分类列表")
    @GetMapping("/listCategory")
    public ResultVo<List<Category>> listCategory() {
        List<Category> categoryList = categoryService.getAllCategoryList();
        return new ResultVo<>("操作成功: 查询分类列表!", categoryList);
    }


}
