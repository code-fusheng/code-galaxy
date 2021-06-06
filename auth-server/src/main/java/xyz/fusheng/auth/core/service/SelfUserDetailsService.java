package xyz.fusheng.auth.core.service;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.tool.feign.UserFeignClientServer;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.entity.User;

import javax.annotation.Resource;

/**
 * @FileName: SelfUserDetailsService
 * @Author: code-fusheng
 * @Date: 2021/6/3 1:56 下午
 * @Version: 1.0
 * @Description: 自定义用户业务逻辑实现
 */

@Service
public class SelfUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SelfUserDetailsService.class);

    @Resource
    private UserFeignClientServer userFeignClientServer;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClientServer.selectUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户["+ username +"]不存在!");
        }
        if (StateEnums.NOT_ENABLE.getCode().equals(user.getIsEnabled())) {
            throw new DisabledException("用户["+ username +"]已被禁用!");
        }
        SelfUser selfUser = new SelfUser();
        BeanUtils.copyProperties(user, selfUser);
        return selfUser;
    }

}
