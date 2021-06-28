package learn.demo.HighLevel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @FileName: ThredLocalDateFormatTest
 * @Author: code-fusheng
 * @Date: 2021/6/23 1:28 下午
 * @Version: 1.0
 * @Description:
 */

public class ThreadLocalDateFormatTest {

    /**
     * SimpleDateFormat 线程不安全，内部数据结构可能会被并发的访问所破坏。
     */
    public static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {

        String format = dateFormat.get().format(new Date());
        System.out.println(format);

    }

}
