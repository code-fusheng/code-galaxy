package xyz.fusheng.exam.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

import java.util.Date;

/**
 * 应用表-用户考试记录表
 */
@ApiModel(value = "xyz-fusheng-UserExamRecord")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ex_user_exam_record")
public class UserExamRecord extends BaseEntity {
    /**
    * 考试记录编号
    */
    @ApiModelProperty(value="考试记录编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
    * 用户编号
    */
    @ApiModelProperty(value="用户编号")
    private Long userId;

    /**
    * 考试编号
    */
    @ApiModelProperty(value="考试编号")
    private Long examId;

    /**
    * 试卷编号
    */
    @ApiModelProperty(value="试卷编号")
    private Long paperId;

    /**
    * 考试成绩
    */
    @ApiModelProperty(value="考试成绩")
    private Integer totalScore;

    /**
    * 是否阅卷 (0:未阅卷；1:已经阅卷)
    */
    @ApiModelProperty(value="是否阅卷 (0:未阅卷；1:已经阅卷)")
    private Integer isMark;

    /**
    * 是否交卷 (0:未交卷；1:已经交卷)
    */
    @ApiModelProperty(value="是否交卷 (0:未交卷；1:已经交卷)")
    private Integer isSubmit;

    /**
    * 阅卷评语
    */
    @ApiModelProperty(value="阅卷评语")
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
    private Date createdTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updatedTime;
}
