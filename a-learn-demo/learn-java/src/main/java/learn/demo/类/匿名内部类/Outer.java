package learn.demo.类.匿名内部类;

/**
 * @FileName: Outer
 * @Author: code-fusheng
 * @Date: 2020/8/18 14:07
 * @version: 1.0
 * Description: 匿名内部类
 * 匿名内部类必须继承一个抽象类或者实现一个接口。
 * 匿名内部类不能定义任何静态成员和静态方法。
 * 当所在的方法的形参需要被匿名内部类使用时，必须声明为 final。
 * 匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。
 */

public class Outer {

    private void test(final int i) {
        new Service() {
            @Override
            public void method() {
                for (int j = 0; j < i; j++) {
                    System.out.println("匿名内部类");
                }
            }
        }.method();
    }

    /**
     * 匿名内部类必须继承或者实现一个已有的接口
     */
    interface Service {
        void method();
    }

}
