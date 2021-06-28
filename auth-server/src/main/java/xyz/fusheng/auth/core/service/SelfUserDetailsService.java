package xyz.fusheng.auth.core.service;

import com.alibaba.nacos.common.utils.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.feign.UserFeignClientServer;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.entity.User;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        List<Menu> menuList = userFeignClientServer.selectMenusByUserId(user.getUserId());
        SelfUser selfUser = new SelfUser();
        // 4. 封装权限信息（权限标识符code）
        List<GrantedAuthority> authorities = null;
        if(CollectionUtils.isNotEmpty(menuList)) {
            authorities = new ArrayList<>();
            for(Menu menu: menuList) {
                // 权限标识
                String permission = menu.getPermission();
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }
        BeanUtils.copyProperties(user, selfUser);
        selfUser.setAuthorities(authorities);
        logger.info("用户信息预览:{}", selfUser);
        return selfUser;
    }

}
