package learn.demo.类.局部内部类;

/**
 * @FileName: Outer
 * @Author: code-fusheng
 * @Date: 2020/8/18 13:52
 * @version: 1.0
 * Description: 局部内部类
 */

public class Outer {

    private int out_a = 1;
    private static int STATIC_b = 2;

    public void testFunctionClass() {
        int inner_c = 3;
        /**
         * 定义在方法内部的类，就是局部内部类
         * 定义在实例方法中的局部类可以访问外部类的所有变量和方法
         */
        class Inner {
            private void fun() {
                System.out.println(out_a);
                System.out.println(STATIC_b);
                System.out.println(inner_c);
            }
        }
        Inner inner = new Inner();
        inner.fun();
    }

    public static void testStaticFunctionClass() {
        int d = 3;
        /**
         * 定义在静态方法中的局部类只能访问外部类的静态变量和方法
         */
        class Inner {
            private void fun() {
                System.out.println(STATIC_b);
                System.out.println(d);
            }
        }
        Inner inner = new Inner();
        inner.fun();
    }
}
