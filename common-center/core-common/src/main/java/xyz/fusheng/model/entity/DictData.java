package xyz.fusheng.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.model.base.BaseEntity;

@ApiModel(value="xyz-fusheng-model-entity-DictData")
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "sys_dict_data")
public class DictData extends BaseEntity {
    /**
    * 字典编码
    */
    @ApiModelProperty(value="字典编码")
    @TableId(type = IdType.ASSIGN_ID)
    private Long dictCode;

    /**
    * 字典标签
    */
    @ApiModelProperty(value="字典标签")
    private String dictLabel;

    /**
    * 字典键值
    */
    @ApiModelProperty(value="字典键值")
    private String dictValue;

    /**
    * 字典类型
    */
    @ApiModelProperty(value="字典类型")
    private String dictType;

    /**
    * 字典排序
    */
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

    @ApiModelProperty(value = "是否逻辑删除(1:已删除/0:未删除)")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建者编号")
    private Long creatorId;

    @ApiModelProperty(value = "修改者编号")
    private Long updaterId;

    @ApiModelProperty(value = "创建者姓名")
    private String creatorName;

    @ApiModelProperty(value = "修改者姓名")
    private String updaterName;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
}