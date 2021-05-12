package xyz.fusheng.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.model.base.BaseDto;

import javax.validation.constraints.NotNull;

/**
 * @FileName: UserExamRecordDto
 * @Author: code-fusheng
 * @Date: 2021/5/11 2:20 下午
 * @Version: 1.0
 * @Description: 用户考试记录传输对象
 */

@Data
@ApiModel("用户考试记录传输对象")
public class UserExamRecordDto extends BaseDto {

    /**
     * 考试记录编号
     */
    @ApiModelProperty(value="考试记录编号")
    @NotNull
    private Long id;

    /**
     * 用户编号
     */
    @ApiModelProperty(value="用户编号")
    @NotNull
    private Long userId;

    /**
     * 考试编号
     */
    @ApiModelProperty(value="考试编号")
    @NotNull
    private Long examId;

    /**
     * 试卷编号
     */
    @ApiModelProperty(value="试卷编号")
    @NotNull
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
    @NotNull
    private Integer isMark;

    /**
     * 是否交卷 (0:未交卷；1:已经交卷)
     */
    @ApiModelProperty(value="是否交卷 (0:未交卷；1:已经交卷)")
    @NotNull
    private Integer isSubmit;

}
