package learn.demo.变量.String;

import org.apache.commons.lang3.StringUtils;

/**
 * @FileName: StringUtilsTest
 * @Author: code-fusheng
 * @Date: 2022/1/6 5:33 下午
 * @Version: 1.0
 * @Description:
 */

public class StringUtilsTest {

    public static void main(String[] args) {

        String str = "qrscene_111111111";
        System.out.println(str.substring(8));

        boolean scene = StringUtils.contains(str, "scene");
        System.out.println(scene);

    }

}
