package xyz.fusheng.bill.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.bill.model.entity.TransRecord;

/**
 * @FileName: TransRecordVo
 * @Author: code-fusheng
 * @Date: 2021/7/6 上午9:27
 * @Version: 1.0
 * @Description:
 */

@Data
public class TransRecordVo extends TransRecord {

    @ApiModelProperty("消费类型")
    private String transCategoryName;

}
