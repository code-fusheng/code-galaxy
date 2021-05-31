package xyz.fusheng.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.exception.BusinessException;
import xyz.fusheng.feign.AuthFeignClientServer;
import xyz.fusheng.model.dto.LoginDto;
import xyz.fusheng.model.entity.User;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.user.service.UserService;

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
        map.add("client_id", "test-server");
        map.add("client_secret", "test");
        Map<String, String> resultMap = authFeignClientServer.getAccessToken("test-server", "test", "password", loginDto.getUsername(), loginDto.getPassword());
        logger.info("{}", resultMap);
        String access_token = resultMap.get("access_token");
        response.setHeader("authorization", "Bearer " + access_token );
        return new ResultVo<>("登录成功!", access_token);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultVo<User> info() {
        User userInfo = authFeignClientServer.info();
        return new ResultVo<>("操作提示: 获取成功!", userInfo);
    }





}
