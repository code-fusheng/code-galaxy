package xyz.fusheng.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

import javax.validation.constraints.NotNull;

/**
 * @FileName: OptionDto
 * @Author: code-fusheng
 * @Date: 2021/4/25 9:46 上午
 * @Version: 1.0
 * @Description: 选项传输层对象
 */

@Data
@ApiModel(value = "选项传输层对象")
public class OptionDto extends BaseDto {

    /**
     * 选项编号
     */
    @ApiModelProperty(value="选项编号")
    private Long optionId;

    /**
     * 试题编号
     */
    @ApiModelProperty(value="试题编号")
    @NotNull(message = "试题编号不能为空!")
    private Long questionId;

    /**
     * 选项内容
     */
    @ApiModelProperty(value="选项内容")
    private String optionContent;

    /**
     * 选项图片
     */
    @ApiModelProperty(value="选项图片")
    private String optionImage;

    /**
     * 选项视频
     */
    @ApiModelProperty(value="选项视频")
    private String optionVideo;

    /**
     * 选项代码
     */
    @ApiModelProperty(value="选项代码")
    private String optionCode;

    /**
     * 选项排序
     */
    @ApiModelProperty(value = "选项排序")
    private Integer optionSort;

    /**
     * 是否正确（0：正确，1：错误）
     */
    @ApiModelProperty(value="是否正确（1：正确，0：错误）")
    private Integer isRight;

    /**
     * 选项分析
     */
    @ApiModelProperty(value="选项分析")
    private String analysis;

}
