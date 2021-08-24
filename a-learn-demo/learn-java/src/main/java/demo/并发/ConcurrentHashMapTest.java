package demo.并发;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: ConcurrentHashMapTest
 * @Author: code-fusheng
 * @Date: 2021/6/24 9:03 上午
 * @Version: 1.0
 * @Description: 多线程安全的散列映射表
 */

public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put("A", "Hello");
        concurrentHashMap.put("B", "World");
        concurrentHashMap.put("C", "Test");

        int size = concurrentHashMap.size();

        System.out.println(size);

    }

}
