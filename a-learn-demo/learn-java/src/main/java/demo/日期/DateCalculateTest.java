package demo.日期;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @FileName: DateCalculateTest
 * @Author: code-fusheng
 * @Date: 2021/8/30 10:54 上午
 * @Version: 1.0
 * @Description: 时间计算
 */

public class DateCalculateTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月dd日");

        Date inputDate = sdf.parse("2020-02-01 00:00:00");
        System.out.println(inputDate);

        System.out.println(sdf2.format(inputDate));

        Date nowDate = new Date();
        System.out.println(nowDate);

        long day = (nowDate.getTime() - inputDate.getTime()) / (24 * 60 * 60 * 1000);
        System.out.println(day);

    }

}
