package demo.新特性.Java8.Lambda;

/**
 * @FileName: LambdaTest
 * @Author: code-fusheng
 * @Date: 2021/8/23 3:02 下午
 * @Version: 1.0
 * @Description: Java Lambda 表达式
 */

public class LambdaTest {

    public static Object function1(String param) {
        System.out.println(param);
        return param;
    }



    public static void main(String[] args) {
        function1("x");
    }

}
