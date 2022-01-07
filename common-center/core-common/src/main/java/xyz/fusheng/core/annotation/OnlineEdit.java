package xyz.fusheng.core.annotation;

import java.lang.annotation.*;

/**
 * @FileName: OnlineEdit
 * @Author: code-fusheng
 * @Date: 2021/12/28 4:18 下午
 * @Version: 1.0
 * @Description: 在线编辑标识注解
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnlineEdit {

    String columnKey() default "";

}
