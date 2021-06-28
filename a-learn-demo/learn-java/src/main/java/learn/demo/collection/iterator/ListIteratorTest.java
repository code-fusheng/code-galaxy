package learn.demo.collection.iterator;

import java.util.*;

/**
 * @FileName: ListIteratorTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 5:15 下午
 * @Version: 1.0
 * @Description:
 */

public class ListIteratorTest {

    public static void main(String[] args) {

        List<String> list = new LinkedList<>();
        list.add("test");
        list.add("hello");
        list.add("world");
        list.add("code");

        ListIterator<String> iterator = list.listIterator();

        iterator.next();
        iterator.add("fusheng");

        ListIterator<String> iterator1 = list.listIterator();
        iterator1.forEachRemaining(System.out::println);

    }

}
