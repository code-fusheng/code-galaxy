package xyz.fusheng.core.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

/**
    * 应用表-分类表
    */
@ApiModel(value="xyz-fusheng-core-model-entity-Category")
@Data
@EqualsAndHashCode(callSuper=true)
public class Category extends BaseEntity {
    /**
    * 分类id
    */
    @ApiModelProperty(value="分类id")
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
    private String categoryImage;

    /**
    * 分类文章数
    */
    @ApiModelProperty(value="分类文章数")
    private Integer articleCount;

    /**
    * 分类级别 默认1
    */
    @ApiModelProperty(value="分类级别 默认1")
    private String level;

    /**
    * 父级id 默认0
    */
    @ApiModelProperty(value="父级id 默认0")
    private Long pid;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态")
    private Boolean state;

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
    private Boolean isEnabled;

    /**
    * 是否逻辑删除(1:已删除/0:未删除)
    */
    @ApiModelProperty(value="是否逻辑删除(1:已删除/0:未删除)")
    private Boolean isDeleted;

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
    private Date createdTime;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private Date updatedTime;
}
