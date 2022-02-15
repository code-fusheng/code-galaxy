package learn.demo.并发.高并发;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: YieldThreadTest
 * @Author: code-fusheng
 * @Date: 2022/2/8 7:36 下午
 * @Version: 1.0
 * @Description: Yield 线程测试
 */

public class YieldThreadTest {

    // 执行次数
    public static final int MAX_TURN = 500;
    // 执行编号
    public static AtomicInteger index = new AtomicInteger(0);

    private static Map<String, AtomicInteger> metric = new HashMap<>();

    static class YieldThread extends Thread {
        static int threadSeqNumber = 1;
        public YieldThread() {
            super("YieldThread-" + threadSeqNumber);
            threadSeqNumber ++;
            metric.put(this.getName(), new AtomicInteger(0));
        }
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN && index.get() < MAX_TURN; i++) {
                System.out.println(getName() +" 优先级:" + getPriority());
                index.incrementAndGet();
                // 统计 + 1
                metric.get(this.getName()).incrementAndGet();
                if (i % 2 == 0) {
                    // 让出权限
                    Thread.yield();
                }
            }
            System.out.println(metric);
            System.out.println(getName() + " 运行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new YieldThread();
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new YieldThread();
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();

        Thread.sleep(1000000);
    }

}
