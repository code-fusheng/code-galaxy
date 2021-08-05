package xyz.fusheng.exam.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

import java.util.Date;

/**
 * 应用表-试题作答表
 */
@ApiModel(value = "xyz-fusheng-model-entity-QuestionReply")
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionReply extends BaseEntity {
    /**
     * 作答编号
    */
    @ApiModelProperty(value="作答编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long replyId;

    /**
    * 试题编号
    */
    @ApiModelProperty(value="试题编号")
    private Long questionId;

    /**
    * 用户编号
    */
    @ApiModelProperty(value="用户编号")
    private Long userId;

    /**
    * 试卷编号
    */
    @ApiModelProperty(value="试卷编号")
    private Long paperId;

    /**
    * 考试编号
    */
    @ApiModelProperty(value="考试编号")
    private Long examId;

    /**
    * 试题类型
    */
    @ApiModelProperty(value="试题类型")
    private Integer questionType;

    /**
    * 用户答案（Object 集合）
    */
    @ApiModelProperty(value="用户答案（Object 集合）")
    private String userOptions;

    /**
    * 正确答案(Object集合)
    */
    @ApiModelProperty(value="正确答案(Object集合)")
    private String rightOptions;

    /**
    * 试题分值
    */
    @ApiModelProperty(value="试题分值")
    private Integer questionScore;

    /**
    * 实际得分
    */
    @ApiModelProperty(value="实际得分")
    private Integer actualScore;

    /**
    * 是否正确（1：正确，0：错误，2：多选，3：少选）
    */
    @ApiModelProperty(value="是否正确（1：正确，0：错误，2：多选，3：少选）")
    private Integer isRight;

    /**
    * 用时
    */
    @ApiModelProperty(value="用时")
    private Integer useTime;

    /**
    * 阅卷人ID
    */
    @ApiModelProperty(value="阅卷人ID")
    private Long readerId;

    /**
    * 阅卷人姓名
    */
    @ApiModelProperty(value="阅卷人姓名")
    private String readerName;

    /**
    * 作答分析
    */
    @ApiModelProperty(value="作答分析")
    private String analysis;

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
    * 创建者编号（作答）
    */
    @ApiModelProperty(value="创建者编号（作答）")
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
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updatedTime;
}
