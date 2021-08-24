package demo.变量.String;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @FileName: StringValueOfTest
 * @Author: code-fusheng
 * @Date: 2021/8/20 4:39 下午
 * @Version: 1.0
 * @Description:
 */

public class StringValueOfTest {

    public static void main(String[] args) {
        String s = "{\n" +
                "    \"status\": 1,\n" +
                "    \"data\": {\n" +
                "        \"total\": 1,\n" +
                "        \"rows\": [\n" +
                "            {\n" +
                "                \"newOwnTaxBalance\": \"10355.02\",\n" +
                "                \"taxIdNumber\": \"91440300MA5DHTAT05\",\n" +
                "                \"ownTaxAmount\": \"\",\n" +
                "                \"publishDate\": \"2018-01-19\",\n" +
                "                \"ownTaxBalance\": \"10355.02\",\n" +
                "                \"type\": \"国税\",\n" +
                "                \"personIdNumber\": \"非公示项\",\n" +
                "                \"legalPersonName\": \"鲜遥遥。\",\n" +
                "                \"taxCategory\": \"增值税\",\n" +
                "                \"taxpayerType\": \"\",\n" +
                "                \"personIdName\": \"\",\n" +
                "                \"name\": \"泰阳高科智能技术深圳有限公司\",\n" +
                "                \"location\": \"深圳市宝安区新安街道翻身路新乐社区E8栋504号\",\n" +
                "                \"department\": \"深圳市国家税务局\",\n" +
                "                \"regType\": \"\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"msg\": \"success\",\n" +
                "    \"requestId\": \"1ab06752-4e47-497d-9b05-c6d3d8d6a978\"\n" +
                "}";
        JSONObject data = JSON.parseObject(String.valueOf(JSON.parseObject(s).get("data")));
         if (data != null && data.containsKey("total") && data.containsKey("rows")) {
            System.out.println(Integer.parseInt(data.getString("total")));
            System.out.println(data.getJSONArray("rows"));
        }
    }

}
