package xyz.fusheng.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.model.base.BaseEntity;

/**
    * 应用表-试卷规则表
    */
@ApiModel(value="xyz-fusheng-exam-PaperRule")
@Data
@EqualsAndHashCode(callSuper=true)
public class PaperRule extends BaseEntity {
    /**
    * 试卷规则编码
    */
    @ApiModelProperty(value="试卷规则编码")
    private Long paperRuleId;

    /**
    * 试卷规则名称
    */
    @ApiModelProperty(value="试卷规则名称")
    private String paperRuleName;

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