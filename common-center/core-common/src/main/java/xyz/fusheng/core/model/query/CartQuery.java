package xyz.fusheng.core.model.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @FileName: CartQueryVo
 * @Author: code-fusheng
 * @Date: 2021/7/15 上午11:25
 * @Version: 1.0
 * @Description: 购物车查询实体参数
 */

@Data
@ApiModel
public class CartQuery {

    private Long userId;

}
