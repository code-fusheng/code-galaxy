package learn.demo.日期;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @FileName: DateCompareToTest
 * @Author: code-fusheng
 * @Date: 2022/4/12 14:51
 * @Version: 1.0
 * @Description:
 */

public class DateCompareToTest {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        System.out.println(sdf.format(calendar.getTime()));

    }

}
