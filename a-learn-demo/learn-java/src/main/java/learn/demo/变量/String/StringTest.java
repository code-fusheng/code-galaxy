package learn.demo.变量.String;

/**
 * @FileName: StringTest
 * @Author: code-fusheng
 * @Date: 2022/1/19 6:09 下午
 * @Version: 1.0
 * @Description:
 */

public class StringTest {

    public static void main(String[] args) {

        String amount = "20000";

        amount = String.valueOf(Float.parseFloat(amount) / 100);

        System.out.println(amount);

    }

}
