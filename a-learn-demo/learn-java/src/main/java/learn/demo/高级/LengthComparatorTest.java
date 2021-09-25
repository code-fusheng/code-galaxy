package learn.demo.高级;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @FileName: LengthComparatorTest
 * @Author: code-fusheng
 * @Date: 2021/6/19 8:14 上午
 * @Version: 1.0
 * @Description: 长度比较器
 */

public class LengthComparatorTest {

    static class LengthComparator implements Comparator<String> {
        @Override
        public int compare(String first, String second) {
            return first.length() - second.length();
        }
    }

    public static void main(String[] args) {
        String[] strings = {"x", "xxx", "xx"};
        Arrays.sort(strings, new LengthComparator());
        System.out.println(Arrays.toString(strings));

        String[] strings2 = {"z", "zzzz", "zz", "zzz"};
        Arrays.sort(strings2, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(strings2));
    }

}
