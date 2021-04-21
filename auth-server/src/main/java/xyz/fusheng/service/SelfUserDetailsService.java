package xyz.fusheng.service;

import org.apache.commons.lang3.ObjectUtils;
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
import org.springframework.util.CollectionUtils;
import xyz.fusheng.constants.CodeConstant;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.feign.UserFeignClientServer;
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
 */

@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private UserFeignClientServer userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户[" + username +"]不存在!");
        }
        if (StateEnums.NOT_ENABLE.getCode().equals(user.getIsEnabled())) {
            throw new DisabledException("该账户已被禁用!");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoleList().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return buildUserDetails(user);
    }

    public UserDetails loadUserByPhone(String phone) {
        User user = userService.selectUserByPhone(phone);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("手机号[" + phone +"]不存在!");
        }
        if (StateEnums.NOT_ENABLE.getCode().equals(user.getIsEnabled())) {
            throw new DisabledException("该账户已被禁用!");
        }
        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(User user) {
        // 角色集合
        Set<String> authorities = new HashSet<>();
        user.getRoleList().forEach(role -> {
            authorities.add(CodeConstant.ROLE_PREFIX + role.getRoleName());
        });
//        user.getMenuList().forEach(menu -> {
//            authorities.add(menu.getPath());
//        });
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorityList
        );
    }
}
