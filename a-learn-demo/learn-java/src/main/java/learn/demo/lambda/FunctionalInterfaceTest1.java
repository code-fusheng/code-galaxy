package learn.demo.lambda;

/**
 * @FileName: FunctionInterfaceTest1
 * @Author: code-fusheng
 * @Date: 2021/6/6 9:53 下午
 * @Version: 1.0
 * @Description: FunctionalInterface测试
 * @FunctionalInterface 注解用于验证接口是否有且仅有一个需要被实现的方法
 */

@FunctionalInterface
public interface FunctionalInterfaceTest1 {

    void function1();

    default void function2() {};

}
