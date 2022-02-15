package learn.demo.并发.原子类;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: AtomicTest1
 * @Author: code-fusheng
 * @Date: 2022/2/11 10:46 下午
 * @Version: 1.0
 * @Description: 原子类测试
 */

public class AtomicTest1 {

    public static void main(String[] args) {

        int tempValue = 0;
        // 定义原子类实例
        AtomicInteger i = new AtomicInteger();

        // getAndSet() 取值，然后设置新值
        tempValue = i.getAndSet(3);
        // tempValue:0 ; i:3
        System.out.println("tempValue:" + tempValue + "; i:" + i.get());

        // getAndIncrement() 取值，然后自增
        tempValue = i.getAndIncrement();
        // tempValue:3 ; i:4
        System.out.println("tempValue:" + tempValue + "; i:" + i.get());

        // getAndAdd() 取值，然后增加5
        tempValue = i.getAndAdd(5);
        // tempValue:4 ; i:9
        System.out.println("tempValue:" + tempValue + "; i:" + i.get());

        // CAS() 比较交换
        boolean flag = i.compareAndSet(9, 100);
        // flag:true ; i:100
        System.out.println("flag:" + flag + "; i:" + i.get());

    }

}
