package learn.demo.高级;

import java.util.Base64;

/**
 * @FileName: Base64Test
 * @Author: code-fusheng
 * @Date: 2022/1/20 11:08 上午
 * @Version: 1.0
 * @Description:
 */

public class Base64Test {

    public static void main(String[] args) {

        String str = "admin-web:$2a$10$O638bUlynam2PMxEmIFKZ.P2SUEZp1B46dOV0RlVT9IlEGx8j/ZIq";

        byte[] bytes = str.getBytes();

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Base 64 加密后：" + encoded);

        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(encoded);

        String decodeStr = new String(decoded);

    }

}
