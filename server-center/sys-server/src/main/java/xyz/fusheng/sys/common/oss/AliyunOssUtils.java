package xyz.fusheng.sys.common.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: AliyunOssUtils
 * @Author: code-fusheng
 * @Date: 2021/8/2 下午3:13
 * @Version: 1.0
 * @Description: 阿里云OSS工具类
 */

@Slf4j
@Component
public class AliyunOssUtils {

    @Resource
    private AliyunOssConfig aliyunOssConfig;

    /**
     * 文件内容上传
     *
     * @param file 文件对象
     * @return
     */
    public String uploadFileToAliyunOss(MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint(), aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
        try {
            ossClient.putObject(aliyunOssConfig.getBucketName(), file.getOriginalFilename(), new ByteArrayInputStream(file.getBytes()));
            // 判断容器是否存在,不存在就创建
            if (!ossClient.doesBucketExist(aliyunOssConfig.getBucketName())) {
                ossClient.createBucket(aliyunOssConfig.getBucketName());
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(aliyunOssConfig.getBucketName());
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        String url = "https://" + aliyunOssConfig.getBucketName() + "." + aliyunOssConfig.getEndpoint() + "/" + file.getOriginalFilename();
        log.info("文件上传返回结果预览:{}", url);
        return url;
    }

    /**
     * 在线资源上传OSS
     *
     * @param url
     * @param namePrefix
     * @return
     */
    public String uploadOnlineSource(String url, String namePrefix) {
        log.info("在线资源Url预览:{}", url);
        List<String> list = Arrays.asList(url.split("\\."));
        String fileType = list.get(list.size() - 1);
        String fileName = namePrefix + System.currentTimeMillis() + "." + fileType;
        log.info("文件名 fileName:{}", fileName);
        String bucketName = aliyunOssConfig.getBucketName();
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            InputStream inputStream = connection.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint(), aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://" + bucketName + "." + aliyunOssConfig.getEndpoint() + "/" + fileName;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {

    }


}
