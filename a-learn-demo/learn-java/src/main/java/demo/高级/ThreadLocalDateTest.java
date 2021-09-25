package demo.高级;

import lombok.Data;

import java.util.Locale;

/**
 * @FileName: ThreadLocalDateTest
 * @Author: code-fusheng
 * @Date: 2021/8/26 10:45 上午
 * @Version: 1.0
 * @Description:
 */

public class ThreadLocalDateTest {

    public static final ThreadLocal<XX> threadLocal = ThreadLocal.withInitial(() -> new XX());

    public static void main(String[] args) {
        XX xx = threadLocal.get();
        xx.setId(1);
        xx.setName("xxx");
        System.out.println(threadLocal.get());
    }

}

@Data
class XX {
    String name;
    Integer id;
}
