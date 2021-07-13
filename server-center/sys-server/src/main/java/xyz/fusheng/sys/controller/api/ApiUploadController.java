package xyz.fusheng.sys.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.sys.common.utils.UploadUtils;

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

    @Autowired
    private UploadUtils uploadUtils;

    /**
     * 文件上传
     *
     * @param file
     * @return url 文件地址
     */
    @RequestMapping("/uploadImage")
    public ResultVo<String> uploadImage(MultipartFile file) throws IOException {
        String url = uploadUtils.uploadImageToAliyunOss(file);
        return new ResultVo<>("操作成功: 文件上传！", url);
    }

}