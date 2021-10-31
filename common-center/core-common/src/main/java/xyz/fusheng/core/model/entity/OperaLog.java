package xyz.fusheng.core.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author code-fusheng
 * @Date: 21-09-15 17:03:34
 */

@TableName ( value ="sys_opera_log" )
@Data
@ApiModel
public class OperaLog implements Serializable{

	private static final long serialVersionUID = 1L;

  	@TableId(value = "id")
	@ApiModelProperty( value="主键")
	private Integer id;

  	@TableField(value = "opera_title" )
	@ApiModelProperty( value="操作模块标题")
	private String operaTitle;

  	@TableField(value = "opera_type" )
	@ApiModelProperty( value="操作类型(见枚举)")
	private Integer operaType;

  	@TableField(value = "request_name" )
	@ApiModelProperty( value="请求方法名称")
	private String requestName;

  	@TableField(value = "request_url" )
	@ApiModelProperty( value="请求方法地址")
	private String requestUrl;

  	@TableField(value = "request_type" )
	@ApiModelProperty( value="请求方法类型")
	private Integer requestType;

  	@TableField(value = "operator_type" )
	@ApiModelProperty( value="操作用户类型")
	private Integer operatorType;

  	@TableField(value = "operator_name" )
	@ApiModelProperty( value="操作人员名称")
	private String operatorName;

  	@TableField(value = "opera_ip" )
	@ApiModelProperty( value="操作IP地址")
	private String operaIp;

  	@TableField(value = "opera_location" )
	@ApiModelProperty( value="操作地理位置")
	private String operaLocation;

  	@TableField(value = "in_param" )
	@ApiModelProperty( value="入参")
	private String inParam;

  	@TableField(value = "out_param" )
	@ApiModelProperty( value="出参")
	private String outParam;

  	@TableField(value = "opera_result" )
	@ApiModelProperty( value="操作结果(见枚举)")
	private Integer operaResult;

  	@TableField(value = "opera_time" )
	@ApiModelProperty( value="操作时间")
	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	private Date operaTime;

}
