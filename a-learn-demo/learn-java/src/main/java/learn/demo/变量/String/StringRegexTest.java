package learn.demo.变量.String;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @FileName: StringRegexTest
 * @Author: code-fusheng
 * @Date: 2022/1/12 11:58 上午
 * @Version: 1.0
 * @Description:
 */

public class StringRegexTest {

    public static void main(String[] args) {

        String template = "${name}案件已创建，案件编号${code}，您可以在工商工作台案件管理中查看案件进度。";
        List<String> strs = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "xxxxxx");
        jsonObject.put("code", "yyyyyy");
        Collection<Object> values = jsonObject.values();
        Object[] objects = values.toArray();
        Pattern pattern = Pattern.compile("\\$\\{.*?\\}");
        Matcher matcher = pattern.matcher(template);
        int index = 0;
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String key = matcher.group().substring(2, matcher.group().length() - 1);
            strs.add(key);
            matcher.appendReplacement(sb, String.valueOf(jsonObject.get(key)));
            index ++;
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
        System.out.println(strs);
    }

}
