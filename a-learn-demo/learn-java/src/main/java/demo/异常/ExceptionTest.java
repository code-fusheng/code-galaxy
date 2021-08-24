package demo.异常;

/**
 * @FileName: ExceptionTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 8:25 上午
 * @Version: 1.0
 * @Description: 异常测试
 */

public class ExceptionTest {

    public static void main(String[] args) throws Throwable {

        try {
            int x = 100/0;
        } catch (Exception e) {
            // throw new RuntimeException("error:" + e.getMessage() + "info:" + e.getClass().getName());
            Throwable t = new Exception("error");
            t.initCause(e);
            throw t;
        }

    }

}
