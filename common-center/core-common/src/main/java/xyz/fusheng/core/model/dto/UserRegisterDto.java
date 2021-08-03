package xyz.fusheng.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.core.model.base.BaseDto;

/**
 * @FileName: RegisterUserDto
 * @Author: code-fusheng
 * @Date: 2021/7/13 下午11:20
 * @Version: 1.0
 * @Description: 注册用户传输对象
 */

@Data
@ApiModel("注册用户传输对象")
public class UserRegisterDto extends BaseDto {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话号码")
    private String phone;

}
