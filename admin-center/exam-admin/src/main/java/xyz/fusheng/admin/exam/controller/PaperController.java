package xyz.fusheng.admin.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.admin.exam.core.service.PaperService;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.PaperDto;
import xyz.fusheng.core.model.vo.PaperVo;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.tool.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: PaperController
 * @Author: code-fusheng
 * @Date: 2021/5/7 6:50 下午
 * @Version: 1.0
 * @Description: 试卷控制类
 */

@RestController
@RequestMapping("/paper")
@Api(tags = "试卷管理", value = "试卷接口管理")
public class PaperController {

    @Resource
    private PaperService paperService;

    @ApiOperation(value = "添加试卷")
    @PostMapping("/savePaper")
    public ResultVo<Object> savePaper(@RequestBody @Validated PaperDto paperDto) {
        paperService.savePaper(paperDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "分页查询试卷列表")
    @PostMapping("/getPaperByPage")
    public ResultVo<Page<PaperVo>> getPaperByPage(@RequestBody Page<PaperVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 试题类型、试题标签、创建时间、更新时间
            String[] sortColumns = {"paper_name", "paper_rule_id", "created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = paperService.getPaperByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

    /**
     * 后台展示试卷详情的时候，
     * 1、调用 getPaperBaseInfoById 获取试卷基础信息
     * 2、调用 getRuleById          获取规则基础信息
     * 3、调用 getQuestionAndOptionsWithAnswersByPageForPaperId    分页查询试卷中的试题以及选项与答案
     */

    @ApiOperation(value = "获取试卷基础信息")
    @GetMapping("/getPaperBaseInfoById/{paperId}")
    public ResultVo<PaperVo> getPaperBaseInfoById(@PathVariable Long paperId) {
        PaperVo paperVo = paperService.getPaperBaseInfoById(paperId);
        return new ResultVo<>("操作提示: 获取成功!", paperVo);
    }

    @ApiOperation(value = "获取考试选用的试卷信息")
    @GetMapping("/getPaperVoListByExamId/{examId}")
    public ResultVo<List<PaperVo>> getPaperVoListByExamId(@PathVariable Long examId) {
        List<PaperVo> paperVoList = paperService.getPaperVoListByExamId(examId);
        return new ResultVo<>("操作提示: 获取成功!", paperVoList);
    }

}
