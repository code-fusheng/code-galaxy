package xyz.fusheng.core.model.bill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.fusheng.core.model.base.BaseEntity;

/**
 * 记账-流水记录表
 */
@ApiModel(value="xyz-fusheng-core-model-bill-entity-TransRecord")
@Data
@EqualsAndHashCode(callSuper=true)
@TableName("bill_trans_record")
public class TransRecord extends BaseEntity {

    /**
    * 流水主键
    */
    @ApiModelProperty(value="流水主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long transId;

    /**
    * 动帐时间
    */
    @ApiModelProperty(value="动帐时间")
    private Date eventTime;

    /**
    * 交易内容
    */
    @ApiModelProperty(value="交易内容")
    private String transContent;

    /**
    * 交易描述
    */
    @ApiModelProperty(value="交易描述")
    private String transDesc;

    /**
    * 交易金额(分)
    */
    @ApiModelProperty(value="交易金额(分)")
    private Long transAmount;

    /**
     * 消费类型
     * {@link Category}
     */
    @ApiModelProperty(value = "消费类型")
    private Long transCategory;

    /**
    * 支付方式
    */
    @ApiModelProperty(value="支付方式")
    private Integer paidMethod;

    /**
    * 单价(分)
    */
    @ApiModelProperty(value="单价(分)")
    private Long unitPrice;

    /**
    * 交易数量
    */
    @ApiModelProperty(value="交易数量")
    private Integer transCount;

    /**
    * 原价
    */
    @ApiModelProperty(value="原价")
    private Long originalPrice;

    /**
    * 折扣价
    */
    @ApiModelProperty(value="折扣价")
    private Long discountPrice;

    /**
    * 1:+ / 0:-
    */
    @ApiModelProperty(value="1:+ / 0:-")
    private Integer direction;

    /**
    * 操作平台(微信/支付宝)
    */
    @ApiModelProperty(value="操作平台(微信/支付宝)")
    private String operaClient;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态")
    private Integer state;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
    * 拓展
    */
    @ApiModelProperty(value="拓展")
    private String memo;

    /**
    * 乐观锁 默认1
    */
    @ApiModelProperty(value="乐观锁 默认1")
    private Integer version;

    /**
    * 是否启用(1:已启用/0:未启用)
    */
    @ApiModelProperty(value="是否启用(1:已启用/0:未启用)")
    private Integer isEnabled;

    /**
    * 是否逻辑删除(1:已删除/0:未删除)
    */
    @ApiModelProperty(value="是否逻辑删除(1:已删除/0:未删除)")
    private Integer isDeleted;

    /**
    * 创建者编号
    */
    @ApiModelProperty(value="创建者编号")
    private Long creatorId;

    /**
    * 修改者编号
    */
    @ApiModelProperty(value="修改者编号")
    private Long updaterId;

    /**
    * 创建者姓名
    */
    @ApiModelProperty(value="创建者姓名")
    private String creatorName;

    /**
    * 修改者姓名
    */
    @ApiModelProperty(value="修改者姓名")
    private String updaterName;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createdTime;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private Date updatedTime;
}
