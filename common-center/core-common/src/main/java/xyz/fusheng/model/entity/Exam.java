package xyz.fusheng.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.model.base.BaseEntity;

/**
 * 应用表-考试表
 */
@ApiModel(value = "xyz-fusheng-model-entity-Exam")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ex_exam")
public class Exam extends BaseEntity {
    /**
     * 考试编号
     */
    @ApiModelProperty(value = "考试编号")
    @TableId(type = IdType.ASSIGN_ID)
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

    /**
     * 考试分析
     */
    @ApiModelProperty(value = "考试分析")
    private String analysis;

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
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updatedTime;
}