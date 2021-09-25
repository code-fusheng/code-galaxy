package learn.demo.类.成员内部类;

/**
 * @FileName: Outer
 * @Author: code-fusheng
 * @Date: 2020/8/18 13:36
 * @version: 1.0
 * Description: 成员内部类
 */

public class Outer {

    /**
     * 静态成员变量
     */
    private static int mark = 1;
    /**
     * 非静态成员变量
     */
    private int flag = 2;

    /**
     * 成员内部类
     */
    class Inner {
        public void visit() {
            // 成员内部类可以访问外部类所有的变量和方法，包括静态和非静态，私有和公有
            System.out.println("visit outer static variable:" + mark);
            System.out.println("visit outer variable:" + flag);
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        // 成员内部类依赖于外部类的实例 创建方式 外部类实例.new 内部类()
        Outer.Inner inner = outer.new Inner();
        inner.visit();
    }

}
