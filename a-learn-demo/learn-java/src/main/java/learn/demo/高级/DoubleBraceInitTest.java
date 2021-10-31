package learn.demo.高级;

import java.util.ArrayList;

/**
 * @FileName: DoubleBraceInitTest
 * @Author: code-fusheng
 * @Date: 2021/6/20 8:51 下午
 * @Version: 1.0
 * @Description: 编程技巧-双括号初始化
 * 双括号初始化(Double Brace Init) 利用内部类语法。
 */

public class DoubleBraceInitTest {

    public static void main(String[] args) {

        // PS: 这里外层括号构建了 ArrayList 的一个匿名子类，内层括号则是一个对象构造块
        ArrayList<String> strings = new ArrayList<String>() {{ add("Hello");add("world"); }};
        System.out.println(strings);

    }

}
