package xyz.fusheng.sys.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @FileName: UploadUtils
 * @Author: code-fusheng
 * @Date: 2021/6/22 10:25 上午
 * @Version: 1.0
 * @Description:
 */

@Component
public class UploadUtils {

    public UploadUtils() {
    }

    public String uploadImageToAliyunOss(MultipartFile file) throws IOException {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4GBfAZSSf7c8JhEPbK3r";
        String accessKeySecret = "VpMu3ShYf30IpaMNlhEDe8dzBclVGT";
        String bucketName = "aliyun-oss-model";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, file.getOriginalFilename(), new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        String url = "https://" + bucketName +"."+ endpoint +"/"+ file.getOriginalFilename();
        return url;
    }

}
