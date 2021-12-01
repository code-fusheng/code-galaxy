package learn.demo.文件.读写;

import java.io.IOException;
import java.io.InputStream;

/**
 * @FileName: InputStreamTest
 * @Author: code-fusheng
 * @Date: 2021/11/30 10:37 下午
 * @Version: 1.0
 * @Description: 读入
 * 核心抽象方法 : abstract int read()
 * PS: 读入一个字节，并返回读入的字节
 * 阻塞
 */

public class InputStreamTest {

    public static void main(String[] args) throws IOException {

        String str1 = "code-fusheng";
        byte[] bytes = str1.getBytes();

    }

}
