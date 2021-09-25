package learn.demo.类.静态内部类;

/**
 * @FileName: Outer
 * @Author: code-fusheng
 * @Date: 2020/8/18 12:40
 * @version: 1.0
 * Description: 静态内部类
 */

public class Outer {

    /**
     * 静态成员变量
     */
    private static int mark = 1;

    /**
     * 静态内部类
     */
    static class StaticInner {
        public void visit() {
            // 静态内部类方法 访问 外部类静态成员变量
            System.out.println("visit outer variable:" + mark);
        }
    }

    public static void main(String[] args) {
        // 静态内部类的创建方式 new 外部类.静态内部类()
        Outer.StaticInner inner = new Outer.StaticInner();
        inner.visit();
    }

}
