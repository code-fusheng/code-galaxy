package xyz.fusheng.core.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @FileName: Card
 * @Author: code-fusheng
 * @Date: 2021/7/15 上午8:57
 * @Version: 1.0
 * @Description: 购物车实体类
 */

@Data
@ApiModel("购物车实体类")
public class Cart {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "下单客户Id", required = true)
    private Long userId;

    @ApiModelProperty(value = "产品名称")
    private String yfProdName;

    @ApiModelProperty(value = "产品SKU-ID")
    private Long skuId;

    @ApiModelProperty(value = "加入时单价")
    private Long joinTimeUnitPrice;

    @ApiModelProperty(value = "实时单价")
    private Long realTimeUnitPrice;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "备注")
    private String remark;

}
