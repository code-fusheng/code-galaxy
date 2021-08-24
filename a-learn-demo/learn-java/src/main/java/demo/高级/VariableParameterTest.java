package demo.高级;

/**
 * @FileName: VariableParameter
 * @Author: code-fusheng
 * @Date: 2021/6/16 4:02 下午
 * @Version: 1.0
 * @Description: 可变参数
 */

public class VariableParameterTest {

    void variableParameterMethod(Integer... numbers) {
        for (Integer n : numbers) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        new VariableParameterTest().variableParameterMethod(1, 2, 3, 4);
    }

}
