package xyz.fusheng.exam.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.entity.Paper;

/**
 * @FileName: PaperVo
 * @Author: code-fusheng
 * @Date: 2021/5/6 11:00 下午
 * @Version: 1.0
 * @Description: 试卷试题对象
 */

@Data
public class PaperVo extends Paper {

    @ApiModelProperty("规则名称")
    private String ruleName;

    @ApiModelProperty("优先级")
    private String priorityLevel;

}
