package xyz.fusheng.article.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

import java.util.Date;

/**
 * @FileName: CategoryDto
 * @Author: code-fusheng
 * @Date: 2021/6/29 5:00 下午
 * @Version: 1.0
 * @Description:
 */

@Data
@ApiModel("文章分类传输对象")
public class CategoryDto extends BaseDto {

    @ApiModelProperty(value="分类id")
    private Long categoryId;

    @ApiModelProperty(value="分类名称")
    private String categoryName;

    @ApiModelProperty(value="分类图标")
    private String categoryImage;

    @ApiModelProperty(value="分类文章数")
    private Integer articleCount;

    @ApiModelProperty(value="分类级别 默认0")
    private Integer level;

    @ApiModelProperty(value="父级id 默认0")
    private Long pid;

    @ApiModelProperty(value="状态")
    private Integer state;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="拓展")
    private String memo;

    @ApiModelProperty(value="乐观锁 默认1")
    private Integer version;

    @ApiModelProperty(value="是否启用(1:已启用/0:未启用)")
    private Integer isEnabled;

    @ApiModelProperty(value="是否逻辑删除(1:已删除/0:未删除)")
    private Integer isDeleted;

    @ApiModelProperty(value="创建者编号")
    private Long creatorId;

    @ApiModelProperty(value="修改者编号")
    private Long updaterId;

    @ApiModelProperty(value="创建者姓名")
    private String creatorName;

    @ApiModelProperty(value="修改者姓名")
    private String updaterName;

    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty(value="修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updatedTime;
}
