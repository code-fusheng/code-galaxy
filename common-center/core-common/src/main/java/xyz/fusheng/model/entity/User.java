package xyz.fusheng.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.model.base.BaseEntity;

/**
 * 系统表-用户表
 */
@ApiModel(value = "xyz-fusheng-model-entity-User")
@Data
@TableName(value = "sys_user")
public class User extends BaseEntity {

    @ApiModelProperty(value = "用户ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String header;

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

    @ApiModelProperty(value = "性别 0:私密 1:男 2:女")
    private Integer sex;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private BigDecimal lng;

    @ApiModelProperty(value = "纬度")
    private BigDecimal lat;

    @ApiModelProperty(value = "Github-Id")
    private String githubId;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拓展")
    private String memo;

    @ApiModelProperty(value = "乐观锁 默认1")
    private Integer version;

    @ApiModelProperty(value = "是否启用(1:已启用/0:未启用)")
    @TableField(value = "is_enabled")
    private Integer isEnabled;

    @ApiModelProperty(value = "是否逻辑删除(1:已删除/0:未删除)")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建者编号")
    private Long creatorId;

    @ApiModelProperty(value = "修改者编号")
    private Long updaterId;

    @ApiModelProperty(value = "创建者姓名")
    private String creatorName;

    @ApiModelProperty(value = "修改者姓名")
    private String updaterName;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)
    private List<Role> roleList;
}