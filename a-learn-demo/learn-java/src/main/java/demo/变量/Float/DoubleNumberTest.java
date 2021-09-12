package demo.变量.Float;

/**
 * @FileName: DoubleNumberTest
 * @Author: code-fusheng
 * @Date: 2021/8/30 11:46 上午
 * @Version: 1.0
 * @Description:
 */

public class DoubleNumberTest {

    public static void main(String[] args) {

        int totalNumber = Integer.parseInt("111");
        int technologyNumber = Integer.parseInt("222");
        float rate = (float) totalNumber / technologyNumber;
        System.out.println(rate);

        float a = 0.35f;
        System.out.println(a >= 0.35f);

    }

}
