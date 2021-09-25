package learn.demo.高级;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @FileName: FilterOddNumberTest
 * @Author: code-fusheng
 * @Date: 2021/8/2 上午9:26
 * @Version: 1.0
 * @Description:
 */

public class FilterOddNumberTest {

    public static void testOne() {
        // 集合初始化
        List<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i < 10; i ++) {
            list1.add(i);
        }
        // 过滤奇数
        List<Integer> list2 = new ArrayList<Integer>();
        for (int val : list1) {
            if (val % 2 == 0) {
                list2.add(val);
            }
        }
        // 打印结果
        for (int val : list2) {
            System.out.println(val);
        }
    }

    public static void testTwo() {
        IntStream
                .range(0, 10)
                .filter(i -> i % 2 == 0)
                .forEach( System.out::println );
    }

    public static void main(String[] args) {
        testTwo();

    }

}
