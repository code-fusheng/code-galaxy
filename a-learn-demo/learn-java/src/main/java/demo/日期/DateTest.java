package demo.日期;

import java.util.Date;

/**
 * @FileName: DateTest
 * @Author: code-fusheng
 * @Date: 2021/6/28 10:32 上午
 * @Version: 1.0
 * @Description:
 */

public class DateTest {

    public static void main(String[] args) {

        Date t = new Date();

        System.out.println(t);

        boolean result = new Date().after(t);
        System.out.println(result);

        t.setTime(5 * 60 * 1000 + t.getTime());

        System.out.println(t);



    }

}
