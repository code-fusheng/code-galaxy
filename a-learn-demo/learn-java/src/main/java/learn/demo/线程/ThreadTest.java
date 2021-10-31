package learn.demo.线程;

/**
 * @FileName: ThreadTest
 * @Author: code-fusheng
 * @Date: 2021/6/23 1:40 下午
 * @Version: 1.0
 * @Description: 线程测试
 */

public class ThreadTest {

    static int i = 1000;

    static void testNum() {
        i --;
        System.out.println(Thread.currentThread().getName() + ":" + i);
    }

    public static void main(String[] args) {

        new Thread(() -> {
            testNum();
        }).start();

    }

}
