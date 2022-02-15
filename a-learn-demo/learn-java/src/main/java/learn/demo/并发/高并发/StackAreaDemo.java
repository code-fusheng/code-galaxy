package learn.demo.并发.高并发;

/**
 * @FileName: StackAreaDemo
 * @Author: code-fusheng
 * @Date: 2022/2/7 9:35 下午
 * @Version: 1.0
 * @Description:
 */

public class StackAreaDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("线程ID:" + Thread.currentThread().getId());
        System.out.println("线程状态:" + Thread.currentThread().getState());
        System.out.println("线程优先级:" + Thread.currentThread().getPriority());
        anotherFun();
        Thread.sleep(100000);
    }

    private static void anotherFun() {
        int a = 1, b = 1;
        int c = a / b;
        anotherFun2();
    }

    private static void anotherFun2() {
        int a = 1, b = 1;
        int c = a / b;
    }

}
