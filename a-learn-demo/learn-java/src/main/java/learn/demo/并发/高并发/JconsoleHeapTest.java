package learn.demo.并发.高并发;

import lombok.SneakyThrows;

/**
 * @FileName: JconsoleHeapTest
 * @Author: code-fusheng
 * @Date: 2022/2/7 9:30 下午
 * @Version: 1.0
 * @Description: JconsoleHeap 测试
 */

public class JconsoleHeapTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000000);
            }
        }).start();

    }

}
