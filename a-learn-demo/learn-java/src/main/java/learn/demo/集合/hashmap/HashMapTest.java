package learn.demo.集合.hashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @FileName: HashMapTest
 * @Author: code-fusheng
 * @Date: 2021/6/23 8:58 上午
 * @Version: 1.0
 * @Description:
 */

public class HashMapTest {

    void testList() {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList arrayList = strings;
        arrayList.add(new Date());
        System.out.println(arrayList.get(0).toString());
    }

    void testCheckedList() {
        ArrayList<String> strings = new ArrayList<>();
        List<String> list = Collections.checkedList(strings, String.class);
        // list.add(new Date());
    }

    public static void main(String[] args) {
        new HashMapTest().testList();
    }

}
