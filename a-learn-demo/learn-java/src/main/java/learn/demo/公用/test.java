package learn.demo.公用;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

/**
 * @FileName: test
 * @Author: code-fusheng
 * @Date: 2021/6/23 8:45 上午
 * @Version: 1.0
 * @Description:
 */

public class test {

    @Data
    @AllArgsConstructor
    static class Consumevo {
        Long amount;
        Long originalPrice;
    }

    public static void main(String[] args) {

        System.out.println(Strings.trimToNull("   xxx xxx  "));

        Consumevo consumevo = new Consumevo(27000L, 27000L);

        if (!Objects.equals(consumevo.getAmount(), consumevo.getOriginalPrice())) {
            System.out.println("订单金额异常");
        }

        System.out.println(27001 != 27000);

        consumevo.setAmount(0L);

        System.out.println(consumevo.getAmount() == 0);

    }

}
