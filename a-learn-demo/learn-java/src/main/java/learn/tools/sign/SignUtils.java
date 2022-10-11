package learn.tools.sign;


import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * @FileName: SignUtils
 * @Author: code-fusheng
 * @Date: 2022/3/29 09:21
 * @Version: 1.0
 * @Description:
 */

public class SignUtils {

    private static final String AppKey = "7bf749ebcf767632b9b63e40c26ff45d";

    private static final String AppSecret = "7bf749ebcf767632b9b63e40c26ff45d";

    public static void main(String[] args) {
        JSONObject params = new JSONObject();
        params.put("userId", "100");
        params.put("username", "code-fusheng");
        params.put("orgId", null);
        params.put("AppSecret", "7bf749ebcf767632b9b63e40c26ff45d");
        AES aes = new AES(AppSecret.getBytes(StandardCharsets.UTF_8));

        String s = aes.encryptBase64(JSONObject.toJSONBytes(params));

        System.out.println(s);
        long timestamp = System.currentTimeMillis();

        System.out.println(aes.decryptStr(s));

    }

}
