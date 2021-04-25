package xyz.fusheng.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.model.base.BaseDto;
import xyz.fusheng.model.entity.Option;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * @FileName: QuestionDto
 * @Author: code-fusheng
 * @Date: 2021/4/24 11:22 下午
 * @Version: 1.0
 * @Description: 试题传输对象
 */

@Data
public class QuestionDto extends BaseDto {

    /**
     * 试题编号
     */
    @ApiModelProperty(value="试题编号")
    private Long questionId;

    /**
     * 题库编号
     */
    @ApiModelProperty(value = "题库编号", example = "")
    @NotNull(message = "题库编号不能为空!")
    private Long repositoryId;

    /**
     * 试卷编号
     */
    @ApiModelProperty(value = "试卷编号")
    private Long paperId;

    /**
     * 试题内容
     */
    @ApiModelProperty(value = "试题内容")
    private String questionContent;

    /**
     * 试题图片
     */
    @ApiModelProperty(value = "试题图片")
    private String questionImage;

    /**
     * 试题视频
     */
    @ApiModelProperty(value = "试题视频")
    private String questionVideo;

    /**
     * 试题代码
     */
    @ApiModelProperty(value = "试题代码")
    private String questionCode;

    /**
     * 试题类型 0：其它，1：单选题，2：多选题，3：填空题，4：判断题，5：简答题
     */
    @ApiModelProperty(value = "试题类型 0：其它，1：单选题，2：多选题，3：填空题，4：判断题，5：简答题")
    private Integer questionTypeId;

    /**
     * 试题标签编号 0：其它，1：基础题，2：提升题，3：开发题
     */
    @ApiModelProperty(value = "试题标签编号 0：其它，1：基础题，2：提升题，3：开发题")
    private Integer questionTagId;

    /**
     * 试题分析
     */
    @ApiModelProperty(value="试题分析")
    private String analysis;

    /**
     * 选项列表
     */
    private List<Option> optionList = new LinkedList<>();

    /**
     * 试题排序
     */
    @ApiModelProperty(value = "试题排序")
    private Integer questionSort;

    /**
     * 试题分数
     */
    @ApiModelProperty(value = "试题分值")
    private Integer questionScore;

}
