package learn.demo.对象;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @FileName: ObjectsTest
 * @Author: code-fusheng
 * @Date: 2022/3/4 10:58 上午
 * @Version: 1.0
 * @Description:
 */

public class ObjectsTest {

    static <T> void test(T... objects) {

        A a = new A();
        B b = new B();
        List<T> collect = Arrays.stream(objects).filter(item -> item instanceof A).collect(Collectors.toList());
        System.out.println(collect);
//        Arrays.stream(objects).forEach(item -> {
//            if (item instanceof A) {
//                a = item;
//            } else if (item instanceof B) {
//                b = item;
//            }
//        });

    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        test(a, b);
    }

}

@Data
class A {
    String a1;
    String a2;
}

@Data
class B {
    String b1;
    String b2;
}
