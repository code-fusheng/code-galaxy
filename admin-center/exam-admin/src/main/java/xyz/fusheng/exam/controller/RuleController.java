package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.exam.service.RuleService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.RuleDto;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.model.vo.RuleVo;
import xyz.fusheng.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: PaperRuleController
 * @Author: code-fusheng
 * @Date: 2021/4/25 3:10 下午
 * @Version: 1.0
 * @Description: 试卷规则控制类
 */

@RestController
@RequestMapping("/rule")
@Api(tags = "试卷规则管理", value = "试卷规则接口管理")
public class RuleController {

    @Resource
    private RuleService ruleService;

    @ApiOperation(value = "添加试卷规则")
    @PostMapping("/saveRule")
    public ResultVo<Object> saveRule(@RequestBody RuleDto ruleDto) {
        ruleService.saveRule(ruleDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    /**
     * 分页查询试卷规则详情
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询试卷规则列表")
    @PostMapping("/getRuleByPage")
    public ResultVo<Page<RuleVo>> getRuleByPage(@RequestBody Page<RuleVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 总分、时长、合格分、单选题数/分数、多选题数/分数、填空题数/分数、判断题数/分数、简答题数/分数
            String[] sortColumns = {"total_score", "total_time", "eligibility_score", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = ruleService.getRuleByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
