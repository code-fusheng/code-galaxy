package learn.demo.并发.高并发;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @FileName: ExecutorsTest
 * @Author: code-fusheng
 * @Date: 2022/2/8 1:52 下午
 * @Version: 1.0
 * @Description: 线程池工厂类
 */

public class ExecutorsTest {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;

    static class RunnableTask implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程运行开始");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
        }
    }

    static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "线程运行开始");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIMES; i ++) {
                int j = i * 10000;
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
            return used;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        fixedThreadPool.execute(new RunnableTask());

        Future<Long> future = fixedThreadPool.submit(new ReturnableTask());
        System.out.println(Thread.currentThread() + " 线程占用时长:" +future.get());

    }

}
