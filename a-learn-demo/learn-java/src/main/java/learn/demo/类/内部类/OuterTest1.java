package learn.demo.类.内部类;

/**
 * @FileName: Outer
 * @Author: code-fusheng
 * @Date: 2020/8/18 14:49
 * @version: 1.0
 * Description:
 */

public class OuterTest1 {

    /**
     * 外部类的成员方法
     */
    void outMethod() {
        int a = 1;
        final int b = 10;
        /**
         * 内部类
         */
        class Inner {
            void InnerMethod() {
                System.out.println(a);
                System.out.println(b);
            }
        }
        Inner inner = new Inner();
        inner.InnerMethod();
    }

    public static void main(String[] args) {
        OuterTest1 outer = new OuterTest1();
        outer.outMethod();
    }

}
