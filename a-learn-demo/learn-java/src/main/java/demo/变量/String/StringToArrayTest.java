package demo.变量.String;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: StringToArrayTest
 * @Author: code-fusheng
 * @Date: 2021/8/30 5:01 下午
 * @Version: 1.0
 * @Description:
 */

public class StringToArrayTest {

    public static void main(String[] args) {

        String strArray = "[1000, 100, 50, 0.5, 0.1, 0.5, null]";

        List<Float> params = JSONObject.parseArray(strArray).toJavaList(Float.class);

        params.forEach(System.out::println);

    }

}
