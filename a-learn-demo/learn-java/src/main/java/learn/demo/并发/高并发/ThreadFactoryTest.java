package learn.demo.并发.高并发;

import javafx.print.Printer;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: ThreadFactoryTest
 * @Author: code-fusheng
 * @Date: 2022/2/8 9:46 下午
 * @Version: 1.0
 * @Description: 线程工厂类
 */

public class ThreadFactoryTest {

    static public class SimpleThreadFactory implements ThreadFactory {
        static AtomicInteger threadNo = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable target) {
            String threadName = "simpleThread-" + threadNo.get();
            System.out.println("创建线程名称:" + threadName);
            threadNo.incrementAndGet();
            Thread thread = new Thread(target, threadName);
            thread.setDaemon(true);
            return thread;
        }
    }

    static class TargetTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;
        public TargetTask() {
            taskName = "task-" + taskNo.get();
            taskNo.incrementAndGet();
        }
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("任务:" + taskName + "开始运行");
            Thread.sleep(1000);
            System.out.println("任务:" + taskName + "结束运行");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(2, new SimpleThreadFactory());
        for (int i = 0; i < 5; i++) {
            threadPool.submit(new TargetTask());
        }
        Thread.sleep(10);
        System.out.println("关闭线程池");
        threadPool.shutdown();

    }

}
