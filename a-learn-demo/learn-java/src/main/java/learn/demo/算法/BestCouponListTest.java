package learn.demo.算法;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import javafx.print.Collation;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @FileName: BestCouponListTest
 * @Author: code-fusheng
 * @Date: 2022/2/8 10:24 上午
 * @Version: 1.0
 * @Description:
 */

public class BestCouponListTest {

    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKeyPro(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        Stream<Map.Entry<K, V>> positiveStream = map.entrySet().stream().filter(x -> Long.parseLong((String) x.getValue()) >= 0L);
        positiveStream.sorted(Map.Entry.<K, V>comparingByKey());
        Stream<Map.Entry<K, V>> negativeStream = map.entrySet().stream().filter(x -> Long.parseLong((String) x.getValue()) < 0L);
        negativeStream.sorted(Map.Entry.<K, V>comparingByKey().reversed());
        if (isDesc) {
            negativeStream.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
            positiveStream.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            negativeStream.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
            positiveStream.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().filter(x -> ((Long) x.getKey()) >= 0).sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> {
                        result.put(e.getKey(), e.getValue());
                    });
            map.entrySet().stream().filter(x -> ((Long) x.getKey()) < 0).sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> {
                        result.put(e.getKey(), e.getValue());
                    });
        } else {
            map.entrySet().stream().filter(x -> ((Long) x.getKey()) <= 0).sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> {
                        result.put(e.getKey(), e.getValue());
                    });
            map.entrySet().stream().filter(x -> ((Long) x.getKey()) > 0).sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> {
                        result.put(e.getKey(), e.getValue());
                    });
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> stringList = new LinkedList<>();
        Multimap<Long, String> result = LinkedListMultimap.create();
        result.put(-1L, "-1");
        result.put(-1L, "-1");
        result.put(-2L, "-2");
        result.put(0L, "0");
        result.put(1L, "1");
        result.put(2L, "2");
        Map<Long, Collection<String>> longStringMap = sortByKey(result.asMap(), true);

        for (Map.Entry<Long, Collection<String>> item : longStringMap.entrySet()) {
            stringList.addAll(item.getValue());
        }
        System.out.println(stringList);
    }


}
