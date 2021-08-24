package demo.高级;

/**
 * @FileName: RuntimeTest
 * @Author: code-fusheng
 * @Date: 2021/7/12 上午11:46
 * @Version: 1.0
 * @Description:
 */

public class RuntimeTest {

    public static void main(String[] args) {

        // 虚拟机可用的最大核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

}
