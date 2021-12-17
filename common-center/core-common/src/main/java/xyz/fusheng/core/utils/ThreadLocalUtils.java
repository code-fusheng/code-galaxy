package xyz.fusheng.core.utils;

import lombok.Data;
import xyz.fusheng.core.model.entity.OperaLog;

/**
 * @FileName: ThreadLocalUtils
 * @Author: code-fusheng
 * @Date: 2021/10/31 20:31
 * @Version: 1.0
 * @Description: 本地线程工具类
 * 本地线程上下文，单例模式 ,用来存储在同一个线程中可能会用到的全局变量
 */

@Data
public class ThreadLocalUtils {

    /**
     * 操作日志实体对象
     */
    private OperaLog operaLog = new OperaLog();

    /**
     * 是否记录日志
     */
    private boolean isLog = false;

    /**
     * 线程本地内存中的变量
     */
    private static ThreadLocal<ThreadLocalUtils> threadLocal = new ThreadLocal<>();

    public static ThreadLocalUtils get() {
        if (threadLocal.get() == null) {
            ThreadLocalUtils threadLocalUtils = new ThreadLocalUtils();
            threadLocal.set(threadLocalUtils);
        }
        ThreadLocalUtils threadLocalUtils = threadLocal.get();
        return  threadLocalUtils;
    }

    public void remove() {
        this.operaLog = null;
        this.isLog = false;
    }

}
