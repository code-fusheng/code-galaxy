package demo.文件;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @FileName: ReadFileExceptionTest
 * @Author: code-fusheng
 * @Date: 2021/6/21 8:43 上午
 * @Version: 1.0
 * @Description: 读取文件异常
 */

public class ReadFileExceptionTest {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new FileInputStream("/Users/zhanghao/IdeaProjects/core/code-galaxy/a-learn-demo/learn-java/file/text.txt"), "UTF-8");
             PrintWriter out = new PrintWriter("text.txt"))
        {
            while (in.hasNext()) {
                out.println(in.next().toUpperCase());
            }
        }
    }

}
