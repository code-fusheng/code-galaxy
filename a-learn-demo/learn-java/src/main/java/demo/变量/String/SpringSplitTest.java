package demo.变量.String;

import java.util.Arrays;
import java.util.List;

/**
 * @FileName: SpringSplitTest
 * @Author: code-fusheng
 * @Date: 2021/8/2 上午9:59
 * @Version: 1.0
 * @Description: 字符串分割测试
 */

public class SpringSplitTest {

    public static void main(String[] args) {

        String keywordStr1 = "测试一,测试二";
        String keywordStr2 = "测试三";

        // 使用正则表达式处理中英文符号
        List<String> list = Arrays.asList(keywordStr1.split("[,，]"));
        list.forEach(System.out::println);

    }

}
