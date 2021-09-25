package demo.新特性.Java8.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName: ItemSortTest
 * @Author: code-fusheng
 * @Date: 2021/8/30 11:33 上午
 * @Version: 1.0
 * @Description:
 */

public class ItemSortTest {
   public static void main(String[] args) {

      List<String> strList = new ArrayList<>(10);
      strList.add("1-1-1");
      strList.add("1-1-2");
      strList.add("1-1-3");
      strList.add("1-1-4");
      strList.add("1-1-5");
      strList.forEach(System.out::println);

      List<String> collect = strList.stream().filter(item -> "1-1-1".equals(item) || "1-1-3".equals(item)).collect(Collectors.toList());
      collect.forEach(System.out::println);
   }
}
