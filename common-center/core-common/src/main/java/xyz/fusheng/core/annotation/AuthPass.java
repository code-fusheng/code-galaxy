package xyz.fusheng.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @FileName: AuthPass
 * @Author: code-fusheng
 * @Date: 2021/9/13 10:22 上午
 * @Version: 1.0
 * @Description:
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPass {

}
