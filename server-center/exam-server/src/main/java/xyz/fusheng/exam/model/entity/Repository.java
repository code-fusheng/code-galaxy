package xyz.fusheng.exam.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

import java.util.Date;

/**
 * 应用表-题库表
 */
@ApiModel(value = "xyz-fusheng-model-entity-Repository")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ex_repository")
public class Repository extends BaseEntity {
    /**
     * 题库编号
     */
    @ApiModelProperty(value = "题库编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long repositoryId;

    /**
     * 题库名称
     */
    @ApiModelProperty(value = "题库名称")
    private String repositoryName;

    /**
     * 题目总数
     */
    @ApiModelProperty(value = "题目总数")
    private Integer questionCount;

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
