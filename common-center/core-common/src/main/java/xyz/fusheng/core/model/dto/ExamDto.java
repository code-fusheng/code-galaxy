package xyz.fusheng.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

import java.util.Date;

/**
 * @FileName: ExamDto
 * @Author: code-fusheng
 * @Date: 2021/5/11 2:42 下午
 * @Version: 1.0
 * @Description: 考试传输对象
 */

@Data
@ApiModel(value = "考试传输对象")
public class ExamDto extends BaseDto {

    /**
     * 考试编号
     */
    @ApiModelProperty(value = "考试编号")
    private Long examId;

    /**
     * 考试名称
     */
    @ApiModelProperty(value = "考试名称")
    private String examName;

    /**
     * 考试描述
     */
    @ApiModelProperty(value = "考试描述")
    private String examDescription;

    /**
     * 考试类型(0:默认 1:日常练习)
     */
    @ApiModelProperty(value = "考试类型(0:默认 1:日常练习)")
    private Integer examType;

    /**
     * 是否公开（1：公开，0：不公开）
     */
    @ApiModelProperty(value = "是否公开（1：公开，0：不公开）")
    private Integer isPublic;

    /**
     * 口令密码
     */
    @ApiModelProperty(value = "口令密码")
    private String password;

    /**
     * 是否限时（1：限时，0：不限时）
     */
    @ApiModelProperty(value = "是否限时（1：限时，0：不限时）")
    private Integer isLimitTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;


}
