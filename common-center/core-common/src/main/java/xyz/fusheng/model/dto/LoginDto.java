package xyz.fusheng.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @FileName: LoginDto
 * @Author: code-fusheng
 * @Date: 2021/4/27 10:25 上午
 * @Version: 1.0
 * @Description: 登录请求实体类
 */

@Data
@ApiModel(value = "登录请求实体类")
public class LoginDto implements Serializable {

    @ApiModelProperty(value = "登录方式 0:用户名密码登录 / 1:手机验证码登录" , required = true)
    private Integer loginType = 0;

    @ApiModelProperty(value = "用户名")
    private String username = "code-fusheng";

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password = "123456";

    @ApiModelProperty(value = "验证码")
    private String code;

}
