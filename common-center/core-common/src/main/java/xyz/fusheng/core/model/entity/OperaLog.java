package xyz.fusheng.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

import java.util.Date;

@ApiModel(value="sys_opera_log")
@Data
@EqualsAndHashCode(callSuper=true)
public class OperaLog extends BaseEntity {
    @ApiModelProperty(value="主键(自增)")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value="操作主题")
    private String operaTitle;

    @ApiModelProperty(value="操作类型")
    private String operaType;

    @ApiModelProperty(value="方法名称")
    private String method;

    @ApiModelProperty(value="请求方式")
    private String requestMethod;

    @ApiModelProperty(value="请求uri")
    private String requestUri;

    @ApiModelProperty(value="操作员类型")
    private String operatorType;

    @ApiModelProperty(value="操作员名称")
    private String operatorName;

    @ApiModelProperty(value="IP地址")
    private String operaIp;

    @ApiModelProperty(value="操作地址")
    private String operaLocation;

    @ApiModelProperty(value="入参")
    private String inputParam;

    @ApiModelProperty(value="出参")
    private String outputParam;

    @ApiModelProperty(value="操作状态(1:正常/0:异常)")
    private Integer state;

    @ApiModelProperty(value="错误信息")
    private String errorMsg;

    @ApiModelProperty(value="操作时间")
    private Date operaTime;

    @ApiModelProperty(value="耗时")
    private Long useTime;

    @ApiModelProperty(value="是否展示(1:展示/0:不展示)")
    private Integer isShow;
}