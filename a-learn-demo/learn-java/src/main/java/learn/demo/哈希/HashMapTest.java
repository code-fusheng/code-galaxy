package learn.demo.哈希;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @FileName: HashMapTest
 * @Author: code-fusheng
 * @Date: 2021/6/11 12:05 下午
 * @Version: 1.0
 * @Description:
 */

public class HashMapTest {



    public static void main(String[] args) {

        Map<String, Object> params = new HashMap<>(16);

        params.put("areaCode", "RegisterArea_");
        params.put("deptFullName", "国家自然科学基金委员会");
        params.put("applyTimes", new ArrayList<String>(){{ add("2021-07-01"); add("2021-07-30"); }});

        Set<String> keys = params.keySet();
        System.out.println(keys);

        keys.forEach(key -> {
            System.out.println(key + "---" + params.get(key).toString());
        });

        System.out.println(params.get("applyTimes"));
        Object o = params.get("applyTimes");

        // 方法一：




    }

}
