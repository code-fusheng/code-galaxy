package xyz.fusheng.auth.common;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: SelfAuthenticationFailureHandler
 * @Author: code-fusheng
 * @Date: 2021/6/4 5:03 下午
 * @Version: 1.0
 * @Description: 自定义认证失败处理器
 */

@Component("selfLoginFailureHandler")
public class SelfLoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(SelfLoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            logger.info("【登录失败】" + exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new ResultVo<>(500, "登录失败: 用户名不存在！")));
        }
        if (exception instanceof LockedException) {
            logger.info("【登录失败】" + exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new ResultVo<>(500, "登录失败: 用户被冻结！")));
        }
        if (exception instanceof BadCredentialsException) {
            logger.info("【登录失败】" + exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new ResultVo<>(500, "登录失败: 用户名密码不正确！")));
        }
    }

}
