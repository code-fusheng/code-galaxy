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
    * 应用表-规则表
    */
@ApiModel(value="xyz-fusheng-exam-PaperRule")
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "ex_rule")
public class Rule extends BaseEntity {
    /**
    * 试卷规则编码
    */
    @ApiModelProperty(value="规则编码")
    @TableId(type = IdType.ASSIGN_ID)
    private Long ruleId;

    /**
    * 试卷规则名称
    */
    @ApiModelProperty(value="规则名称")
    private String ruleName;

    /**
    * 总分 (默认：100)
    */
    @ApiModelProperty(value="总分 (默认：100)")
    private Integer totalScore;

    /**
    * 时长（默认：120min）
    */
    @ApiModelProperty(value="时长（默认：120min）")
    private Integer totalTime;

    /**
    * 合格分（默认：60%）
    */
    @ApiModelProperty(value="合格分（默认：60%）")
    private Integer eligibilityScore;

    /**
    * 题库编号
    */
    @ApiModelProperty(value="题库编号")
    private Long repositoryId;

    /**
    * 单选题数
    */
    @ApiModelProperty(value="单选题数")
    private Integer singleCount;

    /**
    * 单选题分数
    */
    @ApiModelProperty(value="单选题分数")
    private Integer singleScore;

    /**
    * 多选题数
    */
    @ApiModelProperty(value="多选题数")
    private Integer multipleCount;

    /**
    * 多选题分数
    */
    @ApiModelProperty(value="多选题分数")
    private Integer multipleScore;

    /**
    * 填空题数
    */
    @ApiModelProperty(value="填空题数")
    private Integer fillCount;

    /**
    * 填空题分数
    */
    @ApiModelProperty(value="填空题分数")
    private Integer fillScore;

    /**
    * 判断题数
    */
    @ApiModelProperty(value="判断题数")
    private Integer judgeCount;

    /**
    * 判断题分数
    */
    @ApiModelProperty(value="判断题分数")
    private Integer judgeScore;

    /**
    * 简答题数
    */
    @ApiModelProperty(value="简答题数")
    private Integer shortCount;

    /**
    * 简答题分数
    */
    @ApiModelProperty(value="简答题分数")
    private Integer shortScore;

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
    @Version
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
    @TableField(value = "is_deleted")
    @TableLogic(delval = "1", value = "0")
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
