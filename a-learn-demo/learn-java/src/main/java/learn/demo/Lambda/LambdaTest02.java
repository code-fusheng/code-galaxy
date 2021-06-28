package learn.demo.Lambda;

/**
 * @FileName: LambdaTest01
 * @Author: code-fusheng
 * @Date: 2021/6/6 10:13 下午
 * @Version: 1.0
 * @Description: lambda 简化写法
 */

public class LambdaTest02 {

    public static void main(String[] args) {

        int num = 1;

        NoReturnNoParam noReturnNoParam1 = () -> System.out.println("test1");
        noReturnNoParam1.method();

        NoReturnNoParam noReturnNoParam2 = () -> System.out.println("test");
        noReturnNoParam2.method();


        NoReturnOneParam noReturnOneParam = System.out::println;
        noReturnOneParam.method(1);

        NoReturnMultiParam noReturnMultiParam = (param1, param2) -> {
            System.out.println(param1);
            System.out.println(param2);
        };
        noReturnMultiParam.method(1, 2);

        ReturnNoParam returnNoParam1 = new ReturnNoParam() {
            @Override
            public int method() {
                return 0;
            }
        };
        returnNoParam1.method();

        ReturnNoParam returnNoParam2 = () -> num;
        returnNoParam2.method();

        ReturnOneParam returnOneParam = (int param1) -> {
            return param1 + 10;
        };
        returnOneParam.method(100);

        ReturnMultiParam returnMultiParam = Integer::sum;

        returnMultiParam.method(10, 20);

    }

}
