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
 * 应用表-选项表
 */
@ApiModel(value = "xyz-fusheng-model-entity-Option")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ex_option")
public class Option extends BaseEntity {
    /**
     * 选项编号
     */
    @ApiModelProperty(value = "选项编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long optionId;

    /**
     * 试题编号
     */
    @ApiModelProperty(value = "试题编号")
    private Long questionId;

    /**
     * 选项内容
     */
    @ApiModelProperty(value = "选项内容")
    private String optionContent;

    /**
     * 选项图片
     */
    @ApiModelProperty(value = "选项图片")
    private String optionImage;

    /**
     * 选项视频
     */
    @ApiModelProperty(value = "选项视频")
    private String optionVideo;

    /**
     * 选项代码
     */
    @ApiModelProperty(value = "选项代码")
    private String optionCode;

    /**
     * 选项排序
     */
    @ApiModelProperty(value = "选项排序")
    private Integer optionSort;

    /**
     * 是否正确（1：正确，0：错误）
     */
    @ApiModelProperty(value = "是否正确（1：正确，0：错误）")
    private Integer isRight;

    /**
     * 选项分析
     */
    @ApiModelProperty(value = "选项分析")
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