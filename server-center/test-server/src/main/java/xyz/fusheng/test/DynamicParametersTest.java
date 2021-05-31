package xyz.fusheng.test;

/**
 * @FileName: DynamicParametersTest
 * @Author: code-fusheng
 * @Date: 2021/5/27 5:38 下午
 * @Version: 1.0
 * @Description: 动态参数测试
 */


public class DynamicParametersTest {

    public static void listValue(Integer... values) {
        System.out.println(values.length);
    }

    public static void main(String[] args) { listValue(1, 2, 3);
    }

}
