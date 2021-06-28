package xyz.fusheng.admin.test.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.admin.test.common.annotation.UserInfo;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.SecurityUtils;

/**
 * @FileName: TestController
 * @Author: code-fusheng
 * @Date: 2021/6/6 2:08 下午
 * @Version: 1.0
 * @Description: 测试控制类
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private String port;

    @ApiOperation("测试获取用户信息")
    @GetMapping("/testGetUserInfo")
    public ResultVo<Object> testGetUserInfo() {
        // 从 Security 上下文获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("认证信息:{}", authentication);
        // 获取用户详情
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        logger.info("用户信息:{}", details);
        return new ResultVo<>("测试获取用户信息!", details);
    }

    @ApiOperation("测试通过工具类获取用户信息")
    @GetMapping("/testGetUserInfoByUtils")
    @Cacheable
    public ResultVo<Object> testGetUserInfoByUtils() {
        SelfUser userInfo = SecurityUtils.getUserInfo();
        return new ResultVo<>("测试通过工具类获取用户信息!", userInfo);
    }

    @ApiOperation("/测试通过注解获取用户信息")
    @GetMapping("/testGetUserInfoByAnnotation")
    public ResultVo<SelfUser> testGetUserInfoByAnnotation(@UserInfo SelfUser userInfo) {
        return new ResultVo<>(userInfo);
    }

    @PreAuthorize("hasAnyAuthority('test:debug:getPort')")
    @ApiOperation("测试权限控制")
    @GetMapping("/testPreAuthorizeByGetPort")
    public ResultVo<Object> testPreAuthorizeByGetPort() {
        return new ResultVo<>("测试权限控制!", port);
    }

}
