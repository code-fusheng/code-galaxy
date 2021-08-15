package xyz.fusheng.sys.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.sys.common.oss.AliyunOssUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @FileName: UploadController
 * @Author: code-fusheng
 * @Date: 2021/6/22 10:22 上午
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/api/upload")
public class ApiUploadController {

    @Resource
    private AliyunOssUtils aliyunOssUtils;

    /**
     * 文件上传
     *
     * @param file
     * @return url 文件地址
     */
    @PostMapping("/uploadImage")
    public ResultVo<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String url = aliyunOssUtils.uploadFileToAliyunOss(file);
        return new ResultVo<>("操作成功: 文件上传！", url);
    }

}
