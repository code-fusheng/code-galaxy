package xyz.fusheng.bill.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.utils.SecurityUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @FileName: MybatisPlusConfig
 * @Author: code-fusheng
 * @Date: 2021/11/7 21:48
 * @Version: 1.0
 * @Description: MyBatisPlus 配置类
 */

@Configuration
public class MybatisPlusConfig {

    /**
     * 默认填充字段
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                Date nowDate = new Date();
                this.strictInsertFill(metaObject, "createdTime", Date.class, nowDate);
                this.strictInsertFill(metaObject, "updatedTime", Date.class, nowDate);
                // 获取用户信息
                SelfUser userInfo = SecurityUtils.getUserInfo();
                if (!Objects.isNull(userInfo)) {
                    this.strictInsertFill(metaObject, "creatorId", Long.class, userInfo.getUserId());
                    this.strictInsertFill(metaObject, "creatorName", String.class, userInfo.getUsername());
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                Date nowDate = new Date();
                this.setFieldValByName("updatedTime", nowDate, metaObject);
                // 获取用户信息
                SelfUser userInfo = SecurityUtils.getUserInfo();
                if (!Objects.isNull(userInfo)) {
                    this.strictInsertFill(metaObject, "updaterId", Long.class, userInfo.getUserId());
                    this.strictInsertFill(metaObject, "updaterName", String.class, userInfo.getUsername());
                }
            }
        };
    }

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
