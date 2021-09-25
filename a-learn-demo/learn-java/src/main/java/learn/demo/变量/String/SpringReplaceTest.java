package learn.demo.变量.String;

/**
 * @FileName: SpringReplaceTest
 * @Author: code-fusheng
 * @Date: 2021/8/2 上午9:06
 * @Version: 1.0
 * @Description: 字符串替换
 */

public class SpringReplaceTest {

    public static void main(String[] args) {

        String workCardBase64 = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/4QAwRXhpZgAATU0AKgAAAAgAAQExAAI";

        String newWorkCard = workCardBase64.replace(workCardBase64.substring(0, workCardBase64.indexOf("base64,") + 7), "");

        System.out.println(newWorkCard);

    }

}
