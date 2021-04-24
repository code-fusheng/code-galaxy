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
 * 应用表-试题表
 */
@ApiModel(value = "xyz-fusheng-model-entity-Question")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ex_question")
public class Question extends BaseEntity {
    /**
     * 试题编号
     */
    @ApiModelProperty(value = "试题编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long questionId;

    /**
     * 试题内容
     */
    @ApiModelProperty(value = "试题内容")
    private String questionContent;

    /**
     * 试题图片
     */
    @ApiModelProperty(value = "试题图片")
    private String questionImage;

    /**
     * 试题视频
     */
    @ApiModelProperty(value = "试题视频")
    private String questionVideo;

    /**
     * 试题代码
     */
    @ApiModelProperty(value = "试题代码")
    private String questionCode;

    /**
     * 试题类型 0：其它，1：单选题，2：多选题，3：填空题，4：判断题，5：简答题
     */
    @ApiModelProperty(value = "试题类型 0：其它，1：单选题，2：多选题，3：填空题，4：判断题，5：简答题")
    private Integer questionTypeId;

    /**
     * 试题标签编号 0：其它，1：基础题，2：提升题，3：开发题
     */
    @ApiModelProperty(value = "试题标签编号 0：其它，1：基础题，2：提升题，3：开发题")
    private Integer questionTagId;

    /**
     * 试题分析
     */
    @ApiModelProperty(value = "试题分析")
    private String analysis;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拓展")
    private String memo;

    @ApiModelProperty(value = "乐观锁 默认1")
    @Version
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