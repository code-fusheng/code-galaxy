package learn.demo.变量;

import java.util.Optional;

/**
 * @FileName: IntegerTest
 * @Author: code-fusheng
 * @Date: 2022/2/14 10:10 上午
 * @Version: 1.0
 * @Description:
 */

public class IntegerTest {

    public static void main(String[] args) {

        Integer push = null;

        System.out.println(1 == Optional.ofNullable(push).orElse(0));

    }

}
