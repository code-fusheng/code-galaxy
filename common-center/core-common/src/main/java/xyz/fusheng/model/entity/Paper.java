package xyz.fusheng.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.model.base.BaseEntity;

/**
 * 应用表-试卷表
 */
@ApiModel(value = "xyz-fusheng-model-entity-Paper")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_paper")
public class Paper extends BaseEntity {
    /**
     * 试卷编号
     */
    @ApiModelProperty(value = "试卷编号")
    @TableId(type = IdType.ASSIGN_ID)
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
    private Long paperRuleId;

    /**
     * 试卷分析
     */
    @ApiModelProperty(value = "试卷分析")
    private String analysis;

    /**
     * 乐观锁 默认1
     */
    @ApiModelProperty(value = "乐观锁 默认1")
    private Integer version;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拓展")
    private String memo;

    @ApiModelProperty(value = "是否启用(1:已启用/0:未启用)")
    @TableField(value = "is_enabled")
    private Integer isEnabled;

    @ApiModelProperty(value = "是否逻辑删除(1:已删除/0:未删除)")
    @TableField(value = "is_deleted")
    @TableLogic(delval = "1", value = "0")
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