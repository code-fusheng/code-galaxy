package learn.demo.类.内部类;

/**
 * @FileName: OuterTest2
 * @Author: code-fusheng
 * @Date: 2020/8/18 14:58
 * @version: 1.0
 * Description:
 */

public class OuterTest2 {

    /**
     * 外部类变量
     */
    private int age = 12;

    class Inner {
        /**
         * 内部类变量
         */
        private int age = 13;

        public void print() {
            /**
             * 局部类变量
             */
            int age = 14;
            System.out.println("局部类变量:" + age);
            System.out.println("内部类变量:" + this.age);
            System.out.println("外部类变量:" + OuterTest2.this.age);
        }
    }

    public static void main(String[] args) {
        OuterTest2.Inner in = new OuterTest2().new Inner();
        in.print();
    }

}
