package xyz.fusheng.mall.controller.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.core.model.entity.Cart;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.query.CartQuery;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.mall.common.annotation.UserInfo;
import xyz.fusheng.mall.core.service.CartService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: CartController
 * @Author: code-fusheng
 * @Date: 2021/7/14 下午1:55
 * @Version: 1.0
 * @Description: 购物车接口服务
 */

@Slf4j
@Api(tags = "购物车服务")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @RequestMapping(value = "/addCartProduct", method = RequestMethod.POST)
    public ResultVo<Object> addCartProduct(@RequestBody Cart cart, @ApiIgnore @UserInfo SelfUser userInfo) {
        log.info("{}", userInfo);
        cart.setUserId(userInfo.getUserId());
        cartService.addCartProduct(cart);
        return ResultVo.success();
    }

    @RequestMapping(value = "/deleteCartProduct", method = RequestMethod.DELETE)
    public ResultVo<Object> deleteCartProduct(@RequestBody Long[] skuIds, @ApiIgnore @UserInfo SelfUser userInfo) {
        log.info("{}", userInfo);
        cartService.deleteCartProduct(skuIds, userInfo.getUserId());
        return ResultVo.success();
    }

    @RequestMapping(value = "/updateCartProduct", method = RequestMethod.PUT)
    public ResultVo<Object> updateCartProduct() {
        return null;
    }

    @RequestMapping(value = "/queryCartProductList", method = RequestMethod.POST)
    public ResultVo<List<Cart>> queryCartProductList(@RequestBody CartQuery cartQuery) {
        List<Cart> cartList = cartService.queryCartProductList(cartQuery);
        return new ResultVo<>("", cartList);
    }

}
