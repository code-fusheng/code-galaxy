package learn.demo.高级;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @FileName: InstanceofTest
 * @Author: code-fusheng
 * @Date: 2021/8/2 上午9:10
 * @Version: 1.0
 * @Description: Instanceof 测试类
 */

public class InstanceofTest {

    public static void main(String[] args) {
        Object testObject = new ArrayList();
        displayObjectClass(testObject);
    }

    public static void displayObjectClass(Object o) {
        if (o instanceof Vector) {
            System.out.println("对象是 java.util.Vector 类的实例");
        } else if (o instanceof ArrayList) {
            System.out.println("对象是 java.util.ArrayList 类的实例");
        } else {
            System.out.println("对象是 " + o.getClass() + " 类的实例");
        }
    }

}
