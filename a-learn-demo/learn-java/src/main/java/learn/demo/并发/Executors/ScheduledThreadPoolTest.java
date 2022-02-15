package learn.demo.并发.Executors;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: SingleThreadExecutorTest
 * @Author: code-fusheng
 * @Date: 2022/2/8 8:12 下午
 * @Version: 1.0
 * @Description: 调度线程-线程池
 */

public class ScheduledThreadPoolTest {

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

    static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            return RandomUtils.nextLong(1, 200);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            // 参数说明
            // 0: 首次执行任务的延迟时间 500: 每次执行任务的间隔时间 TimeUnit.MILLISECONDS: 毫秒
            threadPool.scheduleAtFixedRate(new TargetTask(),
                    0, 500, TimeUnit.MILLISECONDS);
        }

        Future<Long> future = threadPool.submit(new ReturnableTask());
        try {
            Long result = future.get();
            System.out.println("result:" + result);
        } catch (InterruptedException e) {
            System.out.println();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Thread.sleep(1000);
        threadPool.shutdown();

    }

}
