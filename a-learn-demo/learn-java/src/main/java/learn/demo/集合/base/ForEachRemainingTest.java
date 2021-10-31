package learn.demo.集合.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @FileName: CollectionTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 4:24 下午
 * @Version: 1.0
 * @Description:
 */

public class ForEachRemainingTest {

    public static void main(String[] args) {

        Collection<String> collection = new ArrayList<>();
        collection.add("Hello");
        collection.add("World");

        Iterator<String> iterator = collection.iterator();

        iterator.forEachRemaining(System.out::println);

    }

}
