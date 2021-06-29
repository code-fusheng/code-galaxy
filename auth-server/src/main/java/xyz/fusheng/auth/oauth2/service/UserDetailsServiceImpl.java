package xyz.fusheng.auth.oauth2.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.feign.UserFeignClientServer;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service // 不一定不要少了
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserFeignClientServer userFeignClientServer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 判断用户名是否为空
        if(StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("用户名不能为空");
        }
        // 2. 通过用户名查询数据库中的用户信息
        User user = userFeignClientServer.selectUserByUsername(username);
        if(user == null) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        // 3. 通过用户id去查询数据库的拥有的权限信息
        List<Menu> menuList =
                userFeignClientServer.selectMenusByUserId(user.getUserId());

        SelfUser selfUser = new SelfUser();

        // 4. 封装权限信息（权限标识符code）
        List<GrantedAuthority> authorities = null;
        if(CollectionUtils.isNotEmpty(menuList)) {
            authorities = new ArrayList<>();
            for(Menu menu: menuList) {
                // 权限标识
                String code = menu.getPermission();
                authorities.add(new SimpleGrantedAuthority(code));
            }
        }

        BeanUtils.copyProperties(user, selfUser);
        selfUser.setAuthorities(authorities);
        logger.info("用户信息预览:{}", selfUser);
        return selfUser;
    }
}
