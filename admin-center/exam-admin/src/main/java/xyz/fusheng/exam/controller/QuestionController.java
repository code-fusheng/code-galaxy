package xyz.fusheng.exam.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.exam.service.QuestionService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.QuestionDto;
import xyz.fusheng.model.vo.QuestionVo;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: QuestionController
 * @Author: code-fusheng
 * @Date: 2021/4/24 11:10 下午
 * @Version: 1.0
 * @Description: 试题控制类
 * 试题管理需要注意当前是单独的试题管理还是试卷中的试题管理
 * 同时管理试题时需要考虑到，选项与答案的关系处理
 */

@RestController
@RequestMapping("/question")
@Api(tags = "试题管理", value = "试题管理接口")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @ApiOperation(value = "添加试题")
    @PostMapping("/saveQuestion")
    public ResultVo<Object> saveQuestion(@RequestBody @Validated QuestionDto questionDto) {
        questionDto.setQuestionId(IdWorker.getId());
        questionService.saveQuestion(questionDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "批量删除试题")
    @DeleteMapping("/deleteQuestionByIds")
    public ResultVo<Object> deleteQuestionByIds(@RequestBody Long[] questionIds) {
        // 查询删除的试题是否被试卷引用
        boolean isUsed = questionService.checkQuestionIsUsedByPaper(questionIds);
        if (isUsed) {
            return new ResultVo<>(ResultEnums.BUSINESS_ERROR.getCode(), "操作提示: 批量删除试题目标存在试卷引用");
        }
        questionService.deleteQuestionByIds(questionIds);
        return new ResultVo<>("操作提示: 删除成功!");
    }

    @ApiOperation(value = "更新问题与选项连同答案")
    @PutMapping("/updateQuestionWithOptionsAndAnswers")
    public ResultVo<Object> updateQuestionWithOptionsAndAnswers(@RequestBody @Validated QuestionDto questionDto) {
        questionService.updateQuestionWithOptionsAndAnswers(questionDto);
        return new ResultVo<>("操作提示: 更新成功!");
    }

    @ApiOperation(value = "获取试题与选项连同答案")
    @GetMapping("/getQuestionWithOptionsAndAnswersById/{questionId}")
    public ResultVo<QuestionVo> getQuestionWithOptionsAndAnswersById(@PathVariable Long questionId) {
        QuestionVo questionVo = questionService.getQuestionWithOptionsAndAnswersById(questionId);
        return new ResultVo<>("操作提示: 获取成功!", questionVo);
    }

    @ApiOperation(value = "分页查询试题列表")
    @PostMapping("/getQuestionByPage")
    public ResultVo<Page<QuestionVo>> getQuestionByPage(@RequestBody Page<QuestionVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 试题类型、试题标签、创建时间、更新时间
            String[] sortColumns = {"question_type", "question_tag", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = questionService.getQuestionByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

    @ApiOperation(value = "分页查询试卷中的试题以及选项与答案")
    @PostMapping("/getQuestionAndOptionsWithAnswersByPageForPaperId")
    public ResultVo<Page<QuestionVo>> getQuestionAndOptionsWithAnswersByPageForPaperId(@RequestBody Page<QuestionVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 试题类型、试题标签、创建时间、更新时间
            String[] sortColumns = {"question_type", "question_tag", "created_time", "update_time", "question_sort"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = questionService.getQuestionAndOptionsWithAnswersByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }


}
