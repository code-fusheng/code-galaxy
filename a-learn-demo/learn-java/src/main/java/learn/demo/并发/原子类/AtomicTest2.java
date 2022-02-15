package learn.demo.并发.原子类;

import learn.demo.并发.Executors.FixedThreadPoolTest;
import org.apache.commons.lang3.ThreadUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: AtomicTest2
 * @Author: code-fusheng
 * @Date: 2022/2/11 10:57 下午
 * @Version: 1.0
 * @Description: 原子类测试2
 */

public class AtomicTest2 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch count = new CountDownLatch(10);
        AtomicInteger atomicInteger = new AtomicInteger(0);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicInteger.getAndIncrement();
                }
                count.countDown();
            });
        }

        count.await();

        System.out.println("累加之和:" + atomicInteger.get());

    }

}
