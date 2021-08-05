package xyz.fusheng.exam.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.exam.model.entity.Option;
import xyz.fusheng.exam.model.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: QuestionVo
 * @Author: code-fusheng
 * @Date: 2021/4/24 11:28 下午
 * @Version: 1.0
 * @Description: 试题视图对象
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionVo extends Question {

    @ApiModelProperty("题库Id")
    private Long repositoryId;

    @ApiModelProperty("题库名称")
    private String repositoryName;

    @ApiModelProperty("选项列表")
    private List<Option> optionList = new ArrayList<>();

    @ApiModelProperty("试题分数")
    private Integer questionScore;

}
