package learn.demo.文件.读写;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @FileName: PrintWriterTest
 * @Author: code-fusheng
 * @Date: 2021/11/30 11:20 下午
 * @Version: 1.0
 * @Description: 文本输出
 */

public class PrintWriterTest {
    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.dir" + File.separator + "text.txt");
        PrintWriter out = new PrintWriter(
                new FileOutputStream(path));
        out.print("xxx");

    }
}
