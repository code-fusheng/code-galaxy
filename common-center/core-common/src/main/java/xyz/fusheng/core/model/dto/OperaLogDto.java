package xyz.fusheng.core.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @FileName: OperaLogDto
 * @Author: code-fusheng
 * @Date: 2021/9/15 5:08 下午
 * @Version: 1.0
 * @Description: 操作日志传输对象
 */

@Data
@ApiModel("操作日志传输对象")
public class OperaLogDto {

    @ApiModelProperty( value="主键")
    private Integer id;

    @ApiModelProperty( value="操作模块标题")
    private String operaTitle;

    @ApiModelProperty( value="操作类型(见枚举)")
    private Integer operaType;

    @ApiModelProperty( value="请求方法名称")
    private String requestName;

    @ApiModelProperty( value="请求方法地址")
    private String requestUrl;

    @ApiModelProperty( value="请求方法类型")
    private Integer requestType;

    @ApiModelProperty( value="操作用户类型")
    private Integer operatorType;

    @ApiModelProperty( value="操作人员名称")
    private String operatorName;

    @ApiModelProperty( value="操作IP地址")
    private String operaIp;

    @ApiModelProperty( value="操作地理位置")
    private String operaLocation;

    @ApiModelProperty( value="入参")
    private String inParam;

    @ApiModelProperty( value="出参")
    private String outParam;

    @ApiModelProperty( value="操作结果(见枚举)")
    private Integer operaResult;

    @ApiModelProperty( value="操作时间")
    private Date operaTime;

}
