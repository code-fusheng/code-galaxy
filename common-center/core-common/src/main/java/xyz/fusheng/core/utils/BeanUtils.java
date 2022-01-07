package xyz.fusheng.core.utils;

import org.springframework.beans.BeansException;

/**
 * @FileName: BeanUtils
 * @Author: code-fusheng
 * @Date: 2022/1/4 2:27 下午
 * @Version: 1.0
 * @Description:
 */

public final class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void copyPropertiesAndIgnore(Object source, Object target) throws BeansException {
        copyProperties(source, target, "createdAt", "updatedAt", "createdByName", "updatedByName", "createdBy", "updatedBy", null);
    }

}
