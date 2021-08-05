package xyz.fusheng.sys.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

/**
 * @FileName: DictDataDto
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:39 下午
 * @Version: 1.0
 * @Description: 字典数据传输对象
 */

@Data
@ApiModel(value = "字典数据传输对象")
public class DictDataDto extends BaseDto {

    @ApiModelProperty(value="字典编码")
    @TableId(type = IdType.ASSIGN_ID)
    private Long dictCode;

    @ApiModelProperty(value="字典标签")
    private String dictLabel;

    @ApiModelProperty(value="字典键值")
    private String dictValue;

    @ApiModelProperty(value="字典类型")
    private String dictType;

    @ApiModelProperty(value="字典排序")
    private Integer dictSort;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拓展")
    private String memo;

    @ApiModelProperty(value = "乐观锁 默认1")
    private Integer version;

    @ApiModelProperty(value = "是否启用(1:已启用/0:未启用)")
    @TableField(value = "is_enabled")
    private Integer isEnabled;

}
