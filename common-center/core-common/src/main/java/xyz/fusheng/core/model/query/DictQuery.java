package xyz.fusheng.core.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @FileName: DictQuery
 * @Author: code-fusheng
 * @Date: 2021/9/13 10:41 上午
 * @Version: 1.0
 * @Description: 字典查询参数
 */

@ApiModel("字典查询参数对象")
@Data
public class DictQuery {

    @ApiModelProperty("字典Key XxXx")
    private String dictName;

    @ApiModelProperty("字典详情字段")
    private String[] fields;

}
