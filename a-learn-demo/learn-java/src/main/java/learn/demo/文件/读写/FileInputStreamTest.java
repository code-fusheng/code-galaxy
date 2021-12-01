package learn.demo.文件.读写;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @FileName: FileInputStreamTest
 * @Author: code-fusheng
 * @Date: 2021/11/30 11:02 下午
 * @Version: 1.0
 * @Description: 文件输入流
 */

public class FileInputStreamTest {

    public static void main(String[] args) throws FileNotFoundException {

        String workPath = System.getProperty("user.dir");

        FileInputStream in = new FileInputStream(workPath + File.separator + "test.txt");

    }
}
