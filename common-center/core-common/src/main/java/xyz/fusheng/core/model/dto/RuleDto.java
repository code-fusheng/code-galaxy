package xyz.fusheng.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

/**
 * @FileName: PaperRuleDto
 * @Author: code-fusheng
 * @Date: 2021/5/6 8:32 下午
 * @Version: 1.0
 * @Description: 试卷规则传输对象
 */

@Data
@ApiModel(value = "试卷规则传输对象")
public class RuleDto extends BaseDto {

    /**
     * 试卷规则编码
     */
    @ApiModelProperty(value="试卷规则编码")
    private Long ruleId;

    /**
     * 试卷规则名称
     */
    @ApiModelProperty(value="试卷规则名称")
    private String ruleName;

    /**
     * 总分 (默认：100)
     */
    @ApiModelProperty(value="总分 (默认：100)")
    private Integer totalScore;

    /**
     * 时长（默认：120min）
     */
    @ApiModelProperty(value="时长（默认：120min）")
    private Integer totalTime;

    /**
     * 合格分（默认：60%）
     */
    @ApiModelProperty(value="合格分（默认：60%）")
    private Integer eligibilityScore;

    /**
     * 题库编号
     */
    @ApiModelProperty(value="题库编号")
    private Long repositoryId;

    /**
     * 单选题数
     */
    @ApiModelProperty(value="单选题数")
    private Integer singleCount;

    /**
     * 单选题分数
     */
    @ApiModelProperty(value="单选题分数")
    private Integer singleScore;

    /**
     * 多选题数
     */
    @ApiModelProperty(value="多选题数")
    private Integer multipleCount;

    /**
     * 多选题分数
     */
    @ApiModelProperty(value="多选题分数")
    private Integer multipleScore;

    /**
     * 填空题数
     */
    @ApiModelProperty(value="填空题数")
    private Integer fillCount;

    /**
     * 填空题分数
     */
    @ApiModelProperty(value="填空题分数")
    private Integer fillScore;

    /**
     * 判断题数
     */
    @ApiModelProperty(value="判断题数")
    private Integer judgeCount;

    /**
     * 判断题分数
     */
    @ApiModelProperty(value="判断题分数")
    private Integer judgeScore;

    /**
     * 简答题数
     */
    @ApiModelProperty(value="简答题数")
    private Integer shortCount;

    /**
     * 简答题分数
     */
    @ApiModelProperty(value="简答题分数")
    private Integer shortScore;

}
