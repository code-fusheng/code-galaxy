package xyz.fusheng.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

import javax.validation.constraints.NotNull;

/**
 * @FileName: RepositoryDto
 * @Author: code-fusheng
 * @Date: 2021/4/22 9:57 下午
 * @Version: 1.0
 * @Description: 题库传输对象
 */

@Data
@ApiModel(value = "题库传输对象")
public class RepositoryDto extends BaseDto {

    /**
     * 题库编号
     */
    @ApiModelProperty(value="题库编号")
    private Long repositoryId;

    /**
     * 题库名称
     */
    @ApiModelProperty(value="题库名称")
    @NotNull
    private String repositoryName;

}
