package xyz.fusheng.test.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @FileName: ThreadPoolConfig
 * @Author: code-fusheng
 * @Date: 2021/7/12 上午11:39
 * @Version: 1.0
 * @Description:
 */

@Configuration
public class ThreadPoolConfig {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);
    /**
     * 获取虚拟机可用核心数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 线程池核心数量
     */
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    /**
     * 线程池最大线程容量
     */
    private static final int MAX_POOL_SIZE = CPU_COUNT * 2 + 1;
    /**
     * 线程空闲后的存活时长
     */
    private static final int KEEP_ALIVE_TIME = 30;

    private static final String FIXED_THREAD_POOL_PREFIX = "fixed-thread-pool";

    @Bean
    public ExecutorService newFixedThreadPool() {
        logger.info("初始化固定线程池开始!");
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(FIXED_THREAD_POOL_PREFIX).build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                threadFactory);
        logger.info("初始化固定线程池结束 - 大小:{}, 最大容量:{}",
                threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getMaximumPoolSize());
        return threadPoolExecutor;
    }

}
