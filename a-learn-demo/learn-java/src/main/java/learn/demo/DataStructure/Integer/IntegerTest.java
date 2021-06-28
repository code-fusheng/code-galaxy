package learn.demo.DataStructure.Integer;

/**
 * @FileName: IntegerTest
 * @Author: code-fusheng
 * @Date: 2021/6/10 2:34 下午
 * @Version: 1.0
 * @Description:
 */

public class IntegerTest {

    void test1() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Integer.MIN_VALUE + Integer.MIN_VALUE);

        int a = 2147483647;
        int b = 1;

        b = a >> 1;
        System.out.println(b);
    }

    void test2() {
        Integer n1 = 3;
        int n2 = 3;
        // int -> Integer Integer.valueOf(n2)
        // Integer -> int n1.intValue()

    }

    public static void main(String[] args) {



    }

}
