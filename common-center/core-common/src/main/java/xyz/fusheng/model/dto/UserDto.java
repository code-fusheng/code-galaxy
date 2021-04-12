package xyz.fusheng.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.model.base.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @FileName: UserDto
 * @Author: code-fusheng
 * @Date: 2021/4/12 4:23 下午
 * @Version: 1.0
 * @Description: 用户传输对象
 */

@Data
public class UserDto extends BaseDto {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "参数异常:用户名不能为空!")
    private String username;

    @ApiModelProperty(value = "头像")
    private String header;

    @NotBlank(message = "参数异常:电话号码不能为空!")
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @NotNull(message = "参数异常:性别不能为空!")
    @ApiModelProperty(value = "性别 0:私密 1:男 2:女")
    private Integer sex;

    @ApiModelProperty(value = "地址")
    private String address;

    @NotNull(message = "参数异常:角色不能为空!")
    @ApiModelProperty(value = "角色")
    private Long roleId;

}
