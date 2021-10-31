package learn.demo.线程.创建线程;

/**
 * @FileName: CreateThreadM1
 * @Author: code-fusheng
 * @Date: 2020/8/19 22:27
 * @version: 1.0
 * Description: 创建线程方法1 - 继承 Thread
 */

public class CreateThreadM1 {

    public static void main(String[] args) {
        // 创建自定义的线程子类对象
        MyThread myThread = new MyThread();
        // 调用子类实例的star()方法来启动线程
        myThread.start();
    }

}

/**
 * 定义一个Thread类的子类，重写run方法，将相关逻辑实现，run()方法就是线程要执行的业务逻辑方法
 */
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "run() 方法正在执行...");
    }
}
