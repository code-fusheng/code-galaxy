package learn.demo.ιε.hashmap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: HashMapSyncTest
 * @Author: code-fusheng
 * @Date: 2021/6/23 8:56 δΈε
 * @Version: 1.0
 * @Description:
 */

public class HashMapSyncTest {

    public static void main(String[] args) {

        Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());

    }

}
