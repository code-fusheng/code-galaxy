package xyz.fusheng.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

/**
 * @FileName: PaperDto
 * @Author: code-fusheng
 * @Date: 2021/5/6 11:01 下午
 * @Version: 1.0
 * @Description: 试卷传输对象
 */

@Data
@ApiModel(value = "试卷传输对象")
public class PaperDto extends BaseDto {

    /**
     * 试卷编号
     */
    @ApiModelProperty(value = "试卷编号")
    private Long paperId;

    /**
     * 试卷名称
     */
    @ApiModelProperty(value = "试卷名称")
    private String paperName;

    /**
     * 试卷规则编号
     */
    @ApiModelProperty(value = "试卷规则编号")
    private Long ruleId;

    /**
     * 试卷分析
     */
    @ApiModelProperty(value = "试卷分析")
    private String analysis;


}
