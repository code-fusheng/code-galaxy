
package xyz.fusheng.mall.core.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.model.query.CartQuery;
import xyz.fusheng.mall.common.utils.JsonUtils;
import xyz.fusheng.mall.model.entity.Cart;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName: CartService
 * @Author: code-fusheng
 * @Date: 2021/7/15 上午8:51
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@Service
public class CartService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static String CART_KEY_PREFIX = "cart:uid:";

    /**
     * 添加购物车
     *
     * @param cart
     */
    public void addCartProduct(Cart cart) {
        // 获取用户Id
        Long userId = cart.getUserId();
        // 组装 Redis 的 KEY
        String key = CART_KEY_PREFIX + userId;
        // 获取 Redis Hash 操作对象
        BoundHashOperations<String, Object, Object> hashOperations = stringRedisTemplate.boundHashOps(key);
        // 查询是否存在
        Long skuId = cart.getSkuId();
        Integer num = cart.getNum();
        Boolean result = hashOperations.hasKey(skuId.toString());
        // 购物车已存在该商品，更新商品数量
        if (result) {
            // 存在，获取购物车数据
            cart = JsonUtils.parse(hashOperations.get(String.valueOf(skuId)).toString(), Cart.class);
            if (ObjectUtils.isNotEmpty(cart)) {
                // 修改购物车数量
                cart.setNum(cart.getNum() + num);
            }
        } else {
            // 不存在、新增购物车数据 TODO 根据 SKU 去数据库获取实时产品信息
        }
        // 将购物车写入 Redis
        hashOperations.put(String.valueOf(cart.getSkuId()), JsonUtils.serialize(cart));
    }

    public void deleteCartProduct(Long[] skuIds, Long userId) {
        List<Long> skuIdList = Arrays.asList(skuIds);
        String key = CART_KEY_PREFIX + userId;
        BoundHashOperations<String, Object, Object> hashOperations = stringRedisTemplate.boundHashOps(key);
        skuIdList.forEach(skuId -> hashOperations.delete(String.valueOf(skuId)));
    }

    /**
     * 获取用户购物车(一次性查询)
     *
     * @param cartQuery
     * @return
     */
    public List<Cart> queryCartProductList(CartQuery cartQuery) {
        String key = CART_KEY_PREFIX + cartQuery.getUserId();
        Boolean result = stringRedisTemplate.hasKey(key);
        if (!result) {
            // 不存在直接返回 null
            return null;
        }
        BoundHashOperations<String, Object, Object> hashOperations = stringRedisTemplate.boundHashOps(key);
        List<Object> carts = hashOperations.values();
        if (CollectionUtils.isEmpty(carts)) {
            return null;
        }
        List<Cart> cartList = carts.stream().map(item -> JsonUtils.parse(String.valueOf(item), Cart.class)).collect(Collectors.toList());
        log.info("{}:", cartList);
        return cartList;
    }

}
