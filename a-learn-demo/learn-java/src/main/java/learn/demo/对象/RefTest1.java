package learn.demo.对象;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

/**
 * @FileName: RefTest1
 * @Author: code-fusheng
 * @Date: 2022/3/22 10:34
 * @Version: 1.0
 * @Description:
 */

public class RefTest1 {

    public static void main(String[] args) {

        TestObj testObj = new TestObj(1, 2, 3, "xxxx");
        Class<? extends TestObj> aClass = testObj.getClass();
        Arrays.stream(aClass.getDeclaredFields()).forEach(item -> {
            item.setAccessible(true);
            System.out.println(item.getType().isAssignableFrom(String.class));
            System.out.println(item);
        });

    }

}

@Data
@AllArgsConstructor
class TestObj {
    private Integer id;

    private Integer name;

    private Integer num;

    private Object obj;
}
