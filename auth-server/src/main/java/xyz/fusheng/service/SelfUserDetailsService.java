package xyz.fusheng.service;

import com.alibaba.nacos.common.utils.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import xyz.fusheng.constants.CodeConstant;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.feign.UserFeignClientServer;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.entity.SelfUser;
import xyz.fusheng.model.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @FileName: SelfUserDetailsService
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:20 上午
 * @Version: 1.0
 * @Description: 自定义业务逻辑实现
 * 查询用户信息以及用户权限信息
 */

@Component
public class SelfUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SelfUserDetailsService.class);

    @Autowired
    private UserFeignClientServer userService;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SelfUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、查询用户信息
        User user = userService.selectUserByUsername(username);
        logger.info("登陆用户信息预览:{}", user);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户[" + username +"]不存在!");
        }
        if (StateEnums.NOT_ENABLE.getCode().equals(user.getIsEnabled())) {
            throw new DisabledException("该账户已被禁用!");
        }
        // 2、通过UserId查询用户权限信息
        List<Menu> menuList = userService.selectMenusByUserId(user.getUserId());
        // 3、封装权限信息(code)
        List<GrantedAuthority> authorities = new ArrayList<>();
        //        if (CollectionUtils.isNotEmpty(menuList)) {
        //            for (Menu menu : menuList) {
        //                // 权限标识
        //                authorities.add(new SimpleGrantedAuthority(menu.getPermission()));
        //            }
        //        }
        // 4、构建用户信息
        SelfUser selfUser = new SelfUser();
        BeanUtils.copyProperties(user, selfUser);
        selfUser.setAuthorities(authorities);
        return selfUser;
    }

    /**
     * 查询用户信息根据手机号码
     * @param phone
     * @return
     */
    public SelfUser loadUserByPhone(String phone) {
        User user = userService.selectUserByPhone(phone);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("手机号[" + phone +"]不存在!");
        }
        if (StateEnums.NOT_ENABLE.getCode().equals(user.getIsEnabled())) {
            throw new DisabledException("该账户已被禁用!");
        }
        SelfUser selfUser = new SelfUser();
        BeanUtils.copyProperties(user, selfUser);
        return selfUser;
    }

}
