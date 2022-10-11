package learn.tools.http;

import org.apache.http.entity.ContentType;

/**
 * @FileName: Test
 * @Author: code-fusheng
 * @Date: 2022/3/14 11:00 上午
 * @Version: 1.0
 * @Description:
 */

public class Test {

    public static void main(String[] args) {
        System.out.println(ContentType.APPLICATION_JSON.getMimeType());
    }

}
