package learn.demo.线程.创建线程;

/**
 * @FileName: CreateThreadM2
 * @Author: code-fusheng
 * @Date: 2020/8/19 22:35
 * @version: 1.0
 * Description: 创建线程方法2 - 实现 Runnable 接口
 */

public class CreateThreadM2 {

    public static void main(String[] args) {
        // 创建 MyRunnable 实例 myRunnable
        MyRunnable myRunnable = new MyRunnable();
        // 以 myRunnable 作为参数（target）创建 Thread 对象，该 Thread 对象才是真正的线程对象
        Thread thread = new Thread(myRunnable);
        // 调用线程对象的启动方法 start() 方法
        thread.start();
    }

}

/**
 * 定义Runnable接口实现类MyRunnable，并重写run()方法
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run() 方法执行中");
    }
}
