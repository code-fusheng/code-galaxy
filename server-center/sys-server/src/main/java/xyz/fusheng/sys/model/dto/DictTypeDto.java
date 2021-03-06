package xyz.fusheng.sys.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @FileName: DictTypeDto
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:32 下午
 * @Version: 1.0
 * @Description: 字典类型传输对象
 */

@Data
@ApiModel(value = "字典分类传输对象")
public class DictTypeDto extends BaseDto {

    @ApiModelProperty(value="字典编号")
    private Long dictId;

    @ApiModelProperty(value="字典名称")
    @NotBlank(message = "字典名称不能为空!")
    private String dictName;

    @ApiModelProperty(value="字典类型")
    @NotNull(message = "字典名称不能为空!")
    private String dictType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拓展")
    private String memo;


}
