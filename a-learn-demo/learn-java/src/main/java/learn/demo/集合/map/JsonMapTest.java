package learn.demo.集合.map;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @FileName: JsonMapTest
 * @Author: code-fusheng
 * @Date: 2022/3/4 11:45 上午
 * @Version: 1.0
 * @Description:
 */

public class JsonMapTest {

    public static void main(String[] args) {
        JSONObject initParams = new JSONObject();
        initParams.put("first", JSONObject.toJSONString(new KeyWordDto() {{ setValue("first"); setColor("#409EFF"); }}));
        initParams.put("caseNo", JSONObject.toJSONString(new KeyWordDto() {{ setValue("caseNo"); setColor("#409EFF"); }}));
        initParams.put("amount", JSONObject.toJSONString(new KeyWordDto() {{ setValue("payAmount"); setColor("#409EFF"); }}));
        initParams.put("time", JSONObject.toJSONString(new KeyWordDto() {{ setValue("time"); setColor("#409EFF"); }}));
        initParams.put("state", JSONObject.toJSONString(new KeyWordDto() {{ setValue("childState"); setColor("#409EFF"); }}));
        initParams.put("remark", JSONObject.toJSONString(new KeyWordDto() {{ setValue("remark"); setColor("#409EFF"); }}));

        Set<Map.Entry<String, Object>> entries = initParams.entrySet();

        KeyWordDto keyWordDto = new KeyWordDto();
        String name = keyWordDto.getClass().getName();
        System.out.println(name);
        String[] split = name.split("\\.");
        System.out.println(split[split.length - 1]);

    }

}

@Data
class KeyWordDto {

    private String value;

    private String color;

}
