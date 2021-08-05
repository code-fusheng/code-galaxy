package xyz.fusheng.bill.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

import java.util.Date;

/**
    * 记账-消费分类表
    */
@ApiModel(value="xyz-fusheng-core-model-bill-entity-Category")
@Data
@EqualsAndHashCode(callSuper=true)
@TableName("bill_category")
public class Category extends BaseEntity {
    /**
    * 分类id
    */
    @ApiModelProperty(value="分类id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long categoryId;

    /**
    * 分类名称
    */
    @ApiModelProperty(value="分类名称")
    private String categoryName;

    /**
    * 分类图标
    */
    @ApiModelProperty(value="分类图标")
    private String categoryIcon;

    /**
    * 分类项目数
    */
    @ApiModelProperty(value="分类项目数")
    private Integer itemCount;

    /**
    * 分类级别 默认0
    */
    @ApiModelProperty(value="分类级别 默认0")
    private Integer level;

    /**
    * 父级id 默认0
    */
    @ApiModelProperty(value="父级id 默认0")
    private Long pid;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态")
    private Integer state;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
    * 拓展
    */
    @ApiModelProperty(value="拓展")
    private String memo;

    /**
    * 乐观锁 默认1
    */
    @ApiModelProperty(value="乐观锁 默认1")
    private Integer version;

    /**
    * 是否启用(1:已启用/0:未启用)
    */
    @ApiModelProperty(value="是否启用(1:已启用/0:未启用)")
    private Integer isEnabled;

    /**
    * 是否逻辑删除(1:已删除/0:未删除)
    */
    @ApiModelProperty(value="是否逻辑删除(1:已删除/0:未删除)")
    private Integer isDeleted;

    /**
    * 创建者编号
    */
    @ApiModelProperty(value="创建者编号")
    private Long creatorId;

    /**
    * 修改者编号
    */
    @ApiModelProperty(value="修改者编号")
    private Long updaterId;

    /**
    * 创建者姓名
    */
    @ApiModelProperty(value="创建者姓名")
    private String creatorName;

    /**
    * 修改者姓名
    */
    @ApiModelProperty(value="修改者姓名")
    private String updaterName;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createdTime;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updatedTime;
}
