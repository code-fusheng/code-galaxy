package demo.数据.Map;

import java.util.HashMap;

/**
 * @FileName: HashMapTest
 * @Author: code-fusheng
 * @Date: 2021/8/30 1:38 下午
 * @Version: 1.0
 * @Description:
 */

public class HashMapTest {

 public static void main(String[] args) {

  HashMap<String, Object> hashMap = new HashMap<>(2);

  hashMap.put("key", 2);
  hashMap.put("key", (int) hashMap.get("keya") + 1);

  System.out.println(hashMap);

 }

}
