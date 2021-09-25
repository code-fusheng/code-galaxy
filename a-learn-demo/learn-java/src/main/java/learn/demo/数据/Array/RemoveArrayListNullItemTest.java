package learn.demo.数据.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @FileName: RemoveArrayNullItemTest
 * @Author: code-fusheng
 * @Date: 2021/6/19 8:38 上午
 * @Version: 1.0
 * @Description: 删除数据空元素
 */

public class RemoveArrayListNullItemTest {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<String>(16);
        arrayList.add("x");
        arrayList.add(null);
        arrayList.add("y");
        System.out.println(Arrays.toString(arrayList.toArray()));
        // 条件删除
        arrayList.removeIf(e -> e == null);
        System.out.println(Arrays.toString(arrayList.toArray()));
    }

}
