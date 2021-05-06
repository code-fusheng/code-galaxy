package xyz.fusheng.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.exam.service.RepositoryService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.RepositoryDto;
import xyz.fusheng.model.entity.Repository;
import xyz.fusheng.model.query.RepositoryQuery;
import xyz.fusheng.model.vo.DictDataVo;
import xyz.fusheng.model.vo.RepositoryVo;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: RepositoryController
 * @Author: code-fusheng
 * @Date: 2021/4/22 9:48 下午
 * @Version: 1.0
 * @Description: 题库控制类
 */

@RestController
@RequestMapping("/repository")
@Api(tags = "题库管理", value = "题库管理接口")
public class RepositoryController {

    @Resource
    private RepositoryService repositoryService;

    @ApiOperation(value = "添加题库")
    @PostMapping("/saveRepository")
    public ResultVo<Object> saveRepository(@RequestBody @Validated RepositoryDto repositoryDto) {
        repositoryService.saveRepository(repositoryDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "获取题库详情")
    @GetMapping("/getRepositoryById/{repositoryId}")
    public ResultVo<RepositoryVo> getRepositoryById(@PathVariable Long repositoryId) {
        RepositoryVo repositoryVo = repositoryService.getRepositoryById(repositoryId);
        return new ResultVo<>("操作提示: 获取成功!", repositoryVo);
    }

    @ApiOperation(value = "批量删除题库")
    @DeleteMapping("/deleteRepositoryByIds")
    public ResultVo<Object> deleteRepositoryByIds(@RequestBody Long[] repositoryIds) {
        repositoryService.deleteRepositoryIds(repositoryIds);
        return new ResultVo<>("操作提示: 删除成功!");
    }

    @ApiOperation(value = "修改题库")
    @PutMapping("/updateRepository")
    public ResultVo<Object> updateRepository(@RequestBody RepositoryDto repositoryDto) {
        repositoryDto.setVersion(repositoryService.getRepositoryById(repositoryDto.getRepositoryId()).getVersion());
        repositoryService.updateRepository(repositoryDto);
        return new ResultVo<>("操作提示: 修改成功!");
    }

    @ApiOperation(value = "分页查询题库列表")
    @PostMapping("/getRepositoryByPage")
    public ResultVo<Page<RepositoryVo>> getRepositoryByPage(@RequestBody Page<RepositoryVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 创建时间、更新时间
            String[] sortColumns = {"repository_name", "question_count", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = repositoryService.getRepositoryByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }
}
