package learn.demo.异常;

import java.util.Scanner;

/**
 * @FileName: StackTraceTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 8:56 上午
 * @Version: 1.0
 * @Description: 堆栈轨迹测试
 */

public class StackTraceTest {

    public static int factorial(int n) {
        System.out.println("factorial("+ n + "):");
        Throwable t = new Throwable();
        // StackTraceElement 含有获取当前文件名与当前执行的代码行号的方法，同时还有获得类名与方法名的方法。
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames) {
            System.out.println(f);
        }
        int r;
        if (n < 1) {
            r = 1;
        } else {
            r = n * factorial(n -1);
        }
        System.out.println("return" + r);
        return r;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter n:");
        int n = in.nextInt();
        factorial(n);
    }

}
