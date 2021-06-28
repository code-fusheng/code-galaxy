package learn.demo.HighLevel;

import java.util.Collection;

/**
 * @FileName: VarargsWarningTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 1:42 下午
 * @Version: 1.0
 * @Description:
 */

public class VarargsWarningTest {

    class Pair {
        private String key;
        public Pair() {}
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
    }

    public static <T> void addAll(Collection<T> coll, T... ts) {
        for (T t : ts) {
            coll.add(t);
        }
    }

    public static void main(String[] args) {

    }

}
