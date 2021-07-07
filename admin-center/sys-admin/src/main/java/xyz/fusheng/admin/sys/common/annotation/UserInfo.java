package xyz.fusheng.admin.sys.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @FileName: UserInfo
 * @Author: code-fusheng
 * @Date: 2021/6/16 9:23 上午
 * @Version: 1.0
 * @Description: 自定义用户信息注解
 * PARAMETER 用于参数
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfo {
}
