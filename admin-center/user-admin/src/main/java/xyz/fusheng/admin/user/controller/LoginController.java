package xyz.fusheng.admin.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.tool.feign.AuthFeignClientServer;
import xyz.fusheng.core.model.dto.LoginDto;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.admin.user.core.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @FileName: LoginController
 * @Author: code-fusheng
 * @Date: 2021/4/28 11:12 下午
 * @Version: 1.0
 * @Description:
 */

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @Resource
    private AuthFeignClientServer authFeignClientServer;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVo<Object> login(@RequestBody @Validated LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("{}", loginDto);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        Integer loginType = loginDto.getLoginType();
        Assert.notNull(loginType, "登录方式不为空!");
        switch (loginType) {
            case 0:
                map.add("grant_type", "password");
                map.add("username", loginDto.getUsername());
                map.add("password", loginDto.getPhone());
                break;
            case 1:
                map.add("grant_type", "mobile");
            default:
                throw new BusinessException("不支持的登录方式!");
        }
        map.add("client_id", "user-admin");
        map.add("client_secret", "test");
        Map<String, String> resultMap = authFeignClientServer.getAccessToken("user-admin", "test", "password", loginDto.getUsername(), loginDto.getPassword());
        logger.info("{}", resultMap);
        String access_token = resultMap.get("access_token");
        response.setHeader("authorization", "Bearer " + access_token );
        return new ResultVo<>("登录成功!", access_token);
    }

    /**
     * PS： 当前我们只能够通过 SecurityContextHolder 获取当前用户的用户名, 无法获取用户的全部信息
     * DefaultUserAuthenticationConverter 源码部分 #extractAuthentication 会判断当前 userDetailsService
     * 是否为空  如果为空就返回用户名
     */

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultVo<User> info() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("principal:{}", principal);
        User userInfo = userService.selectUserByUsername(principal.toString());
        logger.info("user:{}", userInfo);
        return new ResultVo<>("操作提示: 获取成功!", userInfo);
    }





}
