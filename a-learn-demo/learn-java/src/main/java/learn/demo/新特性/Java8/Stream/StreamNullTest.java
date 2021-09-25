package learn.demo.新特性.Java8.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName: StreamNullTest
 * @Author: code-fusheng
 * @Date: 2021/8/24 10:40 上午
 * @Version: 1.0
 * @Description:
 */

public class StreamNullTest {

    public static void main(String[] args) {

        ArrayList<AttachFile> attachFiles = new ArrayList<>(2);
        attachFiles.add(new AttachFile("xx", "xx", 1));
        List<AttachFile> collect = attachFiles.stream().filter(attachFile -> attachFile.getFileBusinessType().equals(1)).collect(Collectors.toList());
        System.out.println(CollectionUtils.isEmpty(collect));
        System.out.println(collect.get(0));

    }
}

@Data
@AllArgsConstructor
 class AttachFile {

    private String fileName;

    private String fileUrl;

    private Integer fileBusinessType;
}
