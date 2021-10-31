package learn.demo.异常;

/**
 * @FileName: FinallyReturnTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 8:36 上午
 * @Version: 1.0
 * @Description: finally 语句块返回测试
 */

public class FinallyReturnTest {

    public static int f (int n) {
        try {
            int r = n * n;
            return r;
        } finally {
            if (n == 2) {
                return 0;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(f(2));

    }

}
