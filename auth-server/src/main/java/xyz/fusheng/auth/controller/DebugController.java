package xyz.fusheng.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.model.entity.SelfUser;

/**
 * @FileName: DebugController
 * @Author: code-fusheng
 * @Date: 2021/4/27 9:11 上午
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/debug")
public class DebugController {

    private static Logger logger = LoggerFactory.getLogger(DebugController.class);

    @GetMapping("/testSelfUser")
    public void testSelfUser() {
        SelfUser selfUser = (SelfUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("{}", selfUser);
    }

}
