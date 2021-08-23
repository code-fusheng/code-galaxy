package demo.集合.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @FileName: IteratorRemoveTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 4:34 下午
 * @Version: 1.0
 * @Description: Iterator 元素操作测试
 */

public class IteratorRemoveTest {

    public static void main(String[] args) {

        Collection<String> collection = new ArrayList<>();
        collection.add("test");
        collection.add("hello");
        collection.add("world");
        collection.add("code");

        Iterator<String> iterator = collection.iterator();

        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }

    }

}
