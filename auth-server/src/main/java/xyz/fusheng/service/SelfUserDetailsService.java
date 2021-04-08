package xyz.fusheng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import xyz.fusheng.feign.UserFeignClientServer;
import xyz.fusheng.model.entity.User;

import java.util.HashSet;
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
        Assert.notNull(user, "用户["+ username +"]不存在");
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoleList().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(String.valueOf(authorities)));
    }

}
