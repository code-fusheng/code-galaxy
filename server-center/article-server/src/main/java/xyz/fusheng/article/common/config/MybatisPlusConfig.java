package xyz.fusheng.article.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.utils.SecurityUtils;

import java.util.Objects;

/**
 * @FileName: MybatisPlusConfig
 * @Author: code-fusheng
 * @Date: 2021/9/14 3:04 下午
 * @Version: 1.0
 * @Description: MybatisPlus 配置类
 */

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                SelfUser userInfo = SecurityUtils.getUserInfo();
                if (Objects.isNull(userInfo)) {
                    return;
                }
                this.strictInsertFill(metaObject, "creator_id", Long.class, userInfo.getUserId());
                this.strictInsertFill(metaObject, "creator_name", String.class, userInfo.getUsername());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                SelfUser userInfo = SecurityUtils.getUserInfo();
                if (Objects.isNull(userInfo)) {
                    return;
                }
                this.strictInsertFill(metaObject, "updater_id", Long.class, userInfo.getUserId());
                this.strictInsertFill(metaObject, "updater_name", String.class, userInfo.getUsername());
            }
        };
    }

}
