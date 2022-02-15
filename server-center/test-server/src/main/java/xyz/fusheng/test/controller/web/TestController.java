package xyz.fusheng.test.controller.web;

import com.google.common.base.Stopwatch;
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
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.SecurityUtils;
import xyz.fusheng.test.common.annotation.UserInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private ExecutorService newFixedThreadPool;

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

    @GetMapping("/testThreadPool")
    public void testThreadPool() {
        test();
    }

    public void test() {
        long startTime = System.currentTimeMillis();
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test1();
            }
        });
        logger.info("{}", System.currentTimeMillis() - startTime);
    }

    public void test1() {
        long startTime = System.currentTimeMillis();
        logger.info("test1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("{}", e.getMessage(), e);
        }
        logger.info("{}", System.currentTimeMillis() - startTime);
    }

    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        System.out.println(started.stop().elapsed(TimeUnit.SECONDS));
    }

}
