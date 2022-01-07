package xyz.fusheng.bill.model.dto.socket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import xyz.fusheng.core.annotation.OnlineEdit;

/**
 * @FileName: CompanyRegMsgDto
 * @Author: code-fusheng
 * @Date: 2021/12/28 4:05 下午
 * @Version: 1.0
 * @Description:
 */

public class CompanyRegMsgDto {

    @OnlineEdit(columnKey = "transContent")
    @ApiModelProperty(value = "交易内容")
    private String transContent;

    @OnlineEdit(columnKey = "transDesc")
    @ApiModelProperty(value="交易描述")
    private String transDesc;

}
