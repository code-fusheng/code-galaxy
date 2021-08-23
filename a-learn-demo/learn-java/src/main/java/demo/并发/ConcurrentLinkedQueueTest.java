package demo.并发;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @FileName: ConcurrentLinkedQueueTest
 * @Author: code-fusheng
 * @Date: 2021/6/24 9:05 上午
 * @Version: 1.0
 * @Description: 多线程安全无边界非阻塞队列
 */

public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        concurrentLinkedQueue.add("hello");

    }

}
