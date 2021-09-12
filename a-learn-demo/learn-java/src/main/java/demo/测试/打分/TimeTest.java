package demo.测试.打分;

/**
 * @FileName: TimeTest
 * @Author: code-fusheng
 * @Date: 2021/8/30 8:56 上午
 * @Version: 1.0
 * @Description:
 */

public class TimeTest {

    public static void main(String[] args) {

        String rule = "%d < 365 : false ? true";
        int t = 364;
        System.out.println(String.format(rule, t));

        System.out.println(4 / 3);

    }

}
