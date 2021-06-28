package learn.demo.DataStructure.Array;

import java.util.Arrays;

/**
 * @FileName: ArrayToStringTest
 * @Author: code-fusheng
 * @Date: 2021/6/16 1:11 下午
 * @Version: 1.0
 * @Description:
 */

public class ArrayToStringTest {

    void test1() {
        int[] arr = {1, 2, 3, 4, 5};
        String str = "" + arr;
        System.out.println(str);
        // [I@610455d6
        // PS: 字符串 "[I@610455d6" 前缀[I表示是一个整形数组
    }

    void test2() {
        int[] arr = {1, 2, 3, 4, 5};
        String str = Arrays.toString(arr);
        System.out.println(str);
    }

    void test3() {
        int[][] arr = {{1, 2}, {3, 4}};
        String str = Arrays.deepToString(arr);
        System.out.println(str);
    }


    public static void main(String[] args) {

        ArrayToStringTest test = new ArrayToStringTest();
        //test.test1();
        //test.test2();
        test.test3();

    }

}
