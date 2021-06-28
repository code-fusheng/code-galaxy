package learn.demo.Lambda;

/**
 * @FileName: LambdaInterfaceTest1
 * @Author: code-fusheng
 * @Date: 2021/6/6 10:05 下午
 * @Version: 1.0
 * @Description: Lambda 测试接口
 */

public class LambdaInterfaceTest1 {

}

/**
 * 无返回无参数
 */
interface NoReturnNoParam {
    void method();
}

/**
 * 无返回有一个参数
 */
interface NoReturnOneParam {
    void method(int param1);
}

/**
 * 无返回有多个参数
 */
interface NoReturnMultiParam {
    void method(int param1, int param2);
}

/**
 * 有返回无参数
 */
interface ReturnNoParam {
    int method();
}

/**
 * 有返回有一个参数
 */
interface ReturnOneParam {
    int method(int param1);
}

/**
 * 有返回有N个参数
 */
interface ReturnMultiParam {
    int method(int param1, int param2);
}



