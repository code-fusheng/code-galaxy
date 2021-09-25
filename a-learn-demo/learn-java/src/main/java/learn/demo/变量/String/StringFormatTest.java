package learn.demo.变量.String;

/**
 * @FileName: StringFormatTest
 * @Author: code-fusheng
 * @Date: 2021/8/2 上午8:58
 * @Version: 1.0
 * @Description: 字符串格式化
 */

public class StringFormatTest {

    public static void main(String[] args) {

        long parkId = 1;
        String correctedLicencePlate = "湘T000000";

        String sql = String.format("select sum(dd.identify) from order_fee_detail ofd \n" +
                "join deduction_detail dd on ofd.order_id = dd.order_id\n" +
                "where ofd.park_id = "+ parkId +"\n" +
                "and ofd.licence_plate = '%s'\n" +
                "and dd.deduction_type = 3  \n" +
                "and TO_DAYS(ofd.updated_at) = TO_DAYS(NOW())", correctedLicencePlate);

        System.out.println(sql);

    }

}
