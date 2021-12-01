package learn.demo.文件.读写;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @FileName: DataInputStreamTest
 * @Author: code-fusheng
 * @Date: 2021/11/30 10:56 下午
 * @Version: 1.0
 * @Description: 二进制读文件
 */

public class DataInputStreamTest {

    public static void main(String[] args) throws IOException {

        String workPath = System.getProperty("user.dir");

        FileInputStream in = new FileInputStream(workPath + File.separator + "text.txt");
        DataInputStream dataIn = new DataInputStream(in);
        char c = dataIn.readChar();
        System.out.println(c);

    }

}
