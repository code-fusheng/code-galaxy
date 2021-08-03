package xyz.fusheng.sys.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
        String url = "https://" + bucketName + "." + endpoint + "/" + file.getOriginalFilename();
        return url;
    }

    public String uploadOnlineImageToAliyunOss(String uri) throws IOException {

        uri = "https://aliyun-oss-model.oss-cn-beijing.aliyuncs.com/%E4%BA%91%E6%9C%8D%E8%AE%A2%E5%8D%95%E5%90%8C%E6%AD%A5%E6%8E%A5%E5%8F%A3V1.0.pdf";
        List<String> list = Arrays.asList(uri.split("\\."));
        System.out.println(list);
        String fileType = list.get(list.size() - 1);
        String name = "file" + System.currentTimeMillis();
        URL httpUrl = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
        InputStream inStream = connection.getInputStream();
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4GBfAZSSf7c8JhEPbK3r";
        String accessKeySecret = "VpMu3ShYf30IpaMNlhEDe8dzBclVGT";
        String bucketName = "aliyun-oss-model";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, name + "." + fileType, inStream);
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/" + name + "." + fileType;
        return url;
    }

    public static void main(String[] args) throws IOException {
        String s1 = new UploadUtils().uploadOnlineImageToAliyunOss(null);
        System.out.println(s1);
    }

}
