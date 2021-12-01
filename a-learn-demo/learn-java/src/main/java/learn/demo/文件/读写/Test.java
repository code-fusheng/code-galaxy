package learn.demo.文件.读写;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @FileName: Test
 * @Author: code-fusheng
 * @Date: 2021/11/30 11:00 下午
 * @Version: 1.0
 * @Description:
 */

public class Test {

   public static void main(String[] args) throws IOException {
      String property = System.getProperty("user.dir");
      String path = property + File.separator + "text.txt";
      System.out.println(property);

      String s = new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset());
      System.out.println(s);
   }

}
