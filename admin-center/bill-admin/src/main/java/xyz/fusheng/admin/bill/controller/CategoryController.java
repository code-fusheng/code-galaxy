package xyz.fusheng.admin.bill.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.admin.bill.common.annotation.UserInfo;
import xyz.fusheng.admin.bill.core.service.CategoryService;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.bill.dto.CategoryDto;
import xyz.fusheng.core.model.bill.vo.CategoryVo;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: CategoryController
 * @Author: code-fusheng
 * @Date: 2021/7/5 下午8:41
 * @Version: 1.0
 * @Description: 消费类型控制类
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("添加消费类型")
    @PostMapping("/saveCategory")
    public ResultVo<Object> saveCategory(@RequestBody CategoryDto categoryDto, @ApiIgnore @UserInfo SelfUser userInfo) {
        categoryDto.setCreatorId(userInfo.getUserId());
        categoryDto.setCreatorName(userInfo.getUsername());
        categoryService.saveCategory(categoryDto);
        return new ResultVo<>("操作成功: 添加消费类型!");
    }

    @ApiOperation("分页查询消费类型")
    @PostMapping("/pageCategory")
    public ResultVo<Page<CategoryVo>> pageCategory(@RequestBody Page<CategoryVo> page) {
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 创建时间、更新时间
            String[] sortColumns = {"created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(page.getSortColumn().toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = categoryService.pageCategory(page);
        return new ResultVo<>("操作成功: 分页查询消费类型", page);
    }

}