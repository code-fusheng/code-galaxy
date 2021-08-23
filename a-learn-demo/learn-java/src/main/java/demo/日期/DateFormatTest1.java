package demo.日期;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @FileName: DateFormatTest1
 * @Author: code-fusheng
 * @Date: 2021/8/9 上午11:50
 * @Version: 1.0
 * @Description:
 * 纯数字字符串时间格式化处理 2021 07 29 14 30 20 parse()
 */

public class DateFormatTest1 {

    public static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyyMMddHHmmss"));

    public static void main(String[] args) throws ParseException {

        String timeStr = "20210729143020";

        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = f.parse(timeStr);
        System.out.println(date);
    }

}
