package learn.demo.并发.高并发;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @FileName: CallableAndFuture
 * @Author: code-fusheng
 * @Date: 2022/2/8 1:22 下午
 * @Version: 1.0
 * @Description: 通过 Callable 接口和 Future 接口相结合创建线程
 */

public class CallableAndFutureTest {

    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;

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

    public static void main(String[] args) throws InterruptedException {

        ReturnableTask task = new ReturnableTask();
        FutureTask<Long> futureTask = new FutureTask<Long>(task);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " 正在处理");
        for (int i = 0; i < COMPUTE_TIMES / 2; i ++) {
            int j = i * 10000;
        }
        System.out.println(Thread.currentThread().getName() + " 获取结果中");
        try {
            System.out.println(thread.getName() + " 线程占用时间:" + futureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }

}
