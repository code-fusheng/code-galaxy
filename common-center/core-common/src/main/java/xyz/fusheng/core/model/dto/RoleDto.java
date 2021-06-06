package xyz.fusheng.core.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

/**
 * @FileName: RoleDto
 * @Author: code-fusheng
 * @Date: 2021/5/18 2:52 下午
 * @Version: 1.0
 * @Description: 角色传输对象
 */

@Data
@ApiModel(value = "角色传输对象")
public class RoleDto extends BaseDto {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

}
