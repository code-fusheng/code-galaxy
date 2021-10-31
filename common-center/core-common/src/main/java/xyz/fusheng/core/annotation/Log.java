package xyz.fusheng.core.annotation; /**
 * @author: code-fusheng
 * @Date: 2020/9/4 15:21
 */

import xyz.fusheng.core.enums.BusinessTypeEnum;
import xyz.fusheng.core.enums.OperatorTypeEnum;

import java.lang.annotation.*;

/**
 * @FileName: Log
 * @Author: code-fusheng
 * @Date: 2020/9/4 15:21
 * @version: 1.0
 * Description: 自定义操作日志记录注解
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 操作人类别
     */
    public OperatorTypeEnum operatorType() default OperatorTypeEnum.ADMIN;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

}
