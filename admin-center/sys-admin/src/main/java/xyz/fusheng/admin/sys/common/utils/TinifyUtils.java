package xyz.fusheng.admin.sys.common.utils;

import com.tinify.Source;
import com.tinify.Tinify;
import lombok.extern.slf4j.Slf4j;
import java.io.File;

/**
 * @FileName: TinifyUtils
 * @Author: code-fusheng
 * @Date: 2021/7/5 下午12:28
 * @Version: 1.0
 * @Description: Tinify 工具类
 */

@Slf4j
public class TinifyUtils {

    private static final String API_KEY = "MryQMvp8f4cj0TcF6kXXJbkGrDJBMbnf";

    static {
        Tinify.setKey(API_KEY);
        log.info("compressionCount:{}", Tinify.compressionCount());
    }

    public static void main(String[] args) throws Exception {

        batchCompress("/Users/zhanghao/IdeaProjects/core/code-galaxy/admin-center/base-admin/src/main/resources/static/img", true);
        log.info("压缩结束!");

    }

    public static void batchCompress(String path, Boolean isDeep) throws Exception {
        File filePath = new File(path);
        boolean isDirectory = filePath.isDirectory();
        log.info("{}是否为文件夹:{}",filePath.getName(), isDirectory);
        if (isDirectory && isDeep) {
            File[] listFiles = filePath.listFiles();
            if (listFiles.length > 0) {
                for (File file : listFiles) {
                    batchCompress(file.getPath(), isDeep);
                }
            }
        } else {
            log.info("源文件:{}-文件大小:{}", filePath.getName(), filePath.length());
            Source source = Tinify.fromFile(String.valueOf(filePath));
            source.toFile(String.valueOf(filePath));
            File newFile = new File(String.valueOf(filePath));
            log.info("新文件:{}-文件大小:{}", newFile.getName(), newFile.length());
        }
    }




}
