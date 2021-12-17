package learn.demo.日期;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @FileName: DatelineTest
 * @Author: code-fusheng
 * @Date: 2021/12/14 10:16 上午
 * @Version: 1.0
 * @Description:
 */

public class DatelineTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date now = new Date();
        Integer deadline = 31;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(now.getTime());
        System.out.println(deadline * 24 * 60 * 60 * 1000);
        Long l = now.getTime() + (deadline * 24 * 60 * 60 * 1000);
        System.out.println(l);
        Date parse = simpleDateFormat2.parse(simpleDateFormat2.format(new Date(l)));
        Date date = new Date(parse.getTime() + (24 * 60 * 60 * 1000 - 1000));

        System.out.println(simpleDateFormat1.format(date));

    }

}
