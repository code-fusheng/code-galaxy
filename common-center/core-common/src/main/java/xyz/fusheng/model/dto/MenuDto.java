package xyz.fusheng.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.model.base.BaseDto;

/**
 * @FileName: MenuDto
 * @Author: code-fusheng
 * @Date: 2021/5/18 3:08 下午
 * @Version: 1.0
 * @Description:
 */

@Data
@ApiModel(value = "权限传输对象")
public class MenuDto extends BaseDto {

    @ApiModelProperty(value = "ID")
    private Long menuId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "权限路由")
    private String path;

    @ApiModelProperty(value = "父级id")
    private Integer pid;

    @ApiModelProperty(value = "权限级别 1 2 3")
    private Integer level;

}
