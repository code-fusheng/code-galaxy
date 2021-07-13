package xyz.fusheng.user.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.core.feign.AuthFeignClientServer;
import xyz.fusheng.core.model.dto.LoginDto;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.user.core.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2021/4/8 10:04 上午
 * @Version: 1.0
 * @Description: 用户控制器
 */

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private AuthFeignClientServer authFeignClientServer;

    @GetMapping("/user/debugUser")
    public ResultVo<Object> debugUser() {
        return new ResultVo<>("操作成功!");
    }


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
                break;
        }
        map.add("client_id", "test-server");
        map.add("client_secret", "test");
        Map<String, String> resultMap = authFeignClientServer.getAccessToken("test-server", "test", "password", loginDto.getUsername(), loginDto.getPassword());
        logger.info("{}", resultMap);
        String access_token = resultMap.get("access_token");
//        response.setHeader("authorization", "Bearer " + access_token );
        return new ResultVo<>("登录成功!", access_token);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultVo<User> info() {
        User userInfo = authFeignClientServer.info();
        return new ResultVo<>("操作提示: 获取成功!", userInfo);
    }

}
