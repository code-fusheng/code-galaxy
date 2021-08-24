package demo.响应式;

import reactor.core.publisher.Mono;

/**
 * @FileName: MonoTest1
 * @Author: code-fusheng
 * @Date: 2021/8/18 下午5:12
 * @Version: 1.0
 * @Description:
 */

public class MonoTest1 {

    public static void main(String[] args) {

        Mono.just("Hello World")
                .map(String::toUpperCase)
                .map(x1 -> "Hello, " + x1 + "!")
                .subscribe(System.out::println);

    }

}
