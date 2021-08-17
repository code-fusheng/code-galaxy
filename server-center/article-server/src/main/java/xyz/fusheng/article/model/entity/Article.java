package xyz.fusheng.article.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

import java.util.Date;

/**
 * 应用表-文章表
 * @author code-fusheng
 */
@ApiModel(value = "xyz-fusheng-core-model-entity-Article")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ar_article")
public class Article extends BaseEntity {
    /**
    * 文章id
    */
    @ApiModelProperty(value="文章id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    /**
    * 文章标题
    */
    @ApiModelProperty(value="文章标题")
    private String articleTitle;

    /**
    * 文章作者id
    */
    @ApiModelProperty(value="文章作者id")
    private Long authorId;

    /**
    * 文章封面
    */
    @ApiModelProperty(value="文章封面")
    private String articleImage;

    /**
    * 文章描述
    */
    @ApiModelProperty(value="文章描述")
    private String articleDesc;

    /**
    * 文章内容
    */
    @ApiModelProperty(value="文章内容")
    private String articleContent;

    /**
    * 附件
     * TODO: 后续考虑附件单独成一体系
    */
    @ApiModelProperty(value="附件")
    private String articleAttachments;

    /**
    * 编辑内容
    */
    @ApiModelProperty(value="编辑内容")
    private String editContent;

    /**
    * 编辑模式
    */
    @ApiModelProperty(value="编辑模式")
    private String editModel;

    @ApiModelProperty(value = "用时/s")
    private Long useTime;

    /**
    * 文章类型
    */
    @ApiModelProperty(value="文章类型")
    private Long articleCategory;

    /**
    * 点赞量 默认0
    */
    @ApiModelProperty(value="点赞量 默认0")
    private Integer goodCount;

    /**
    * 阅读量 默认0
    */
    @ApiModelProperty(value="阅读量 默认0")
    private Integer readCount;

    /**
    * 收藏量 默认0
    */
    @ApiModelProperty(value="收藏量 默认0")
    private Integer collectCount;

    /**
    * 评论量 默认0
    */
    @ApiModelProperty(value="评论量 默认0")
    private Integer commentCount;

    /**
    * 来源标记（0:站内/1:站外/2:其他）
    */
    @ApiModelProperty(value="来源标记（0:站内/1:站外/2:其他）")
    private String sourceMark;

    /**
    * 文章来源
    */
    @ApiModelProperty(value="文章来源")
    private String articleSource;

    /**
    * 状态-发布(0:未发布/1:延时发布/2:发布)
    */
    @ApiModelProperty(value="状态-发布(0:草稿/1:延时发布/2:已发布)")
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
     * 是否公开（0:公开/1:私有）
     */
    @ApiModelProperty(value = "是否公开（0:公开/1:私有）")
    private Integer isPublish;

    /**
    * 是否置顶(1:置顶/0:默认)
    */
    @ApiModelProperty(value="是否置顶(1:置顶/0:默认)")
    private Integer isTop;

    /**
    * 是否启用(1:已启用/0:未启用)
    */
    @ApiModelProperty(value="是否启用(1:已启用/0:未启用)")
    private Integer isEnabled;

    /**
    * 是否逻辑删除(1:已删除/0:未删除)
    */
    @ApiModelProperty(value="是否逻辑删除(1:已删除/0:未删除)")
    @TableLogic
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
