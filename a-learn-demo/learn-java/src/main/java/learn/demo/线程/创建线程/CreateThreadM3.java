package learn.demo.线程.创建线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @FileName: CreateThreadM3
 * @Author: code-fusheng
 * @Date: 2020/8/19 22:42
 * @version: 1.0
 * Description: 创建线程方法3 - 实现 Callable 接口
 */

public class CreateThreadM3 {

    public static void main(String[] args) {
        // 以myCallable为参数创建FutureTask对象
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
        // 将FutureTask作为参数创建Thread对象
        Thread thread = new Thread(futureTask);
        // 调用线程对象的start()方法
        thread.start();
        try {
            Thread.sleep(1000);
            System.out.println("返回结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 创建实现Callable接口的类myCallable
 */
class MyCallable implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " run() 方法正在执行...");
        return 1;
    }
}
