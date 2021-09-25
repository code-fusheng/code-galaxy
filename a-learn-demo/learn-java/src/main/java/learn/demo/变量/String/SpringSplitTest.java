package learn.demo.变量.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String key3 = "https://fengqing-cts-private.gongsibao.com/ctsUpfile/162977660632669D8695D/62360667792616e632136d0accf5ea9179ae56af255a1.docx?Expires=1661312618&OSSAccessKeyId=LTAI4JXJQMbTL2EQ&Signature=D9OQHBY8jSxJjabpyJtLklUkYJk%3D";
        // 使用正则表达式处理中英文符号

        Pattern aa = Pattern.compile(".*(\\..+?)\\?.*?");
        Matcher matcher = aa.matcher(key3);
        if(matcher.matches()){
            System.out.println(matcher.group(1));
        }

    }

}
