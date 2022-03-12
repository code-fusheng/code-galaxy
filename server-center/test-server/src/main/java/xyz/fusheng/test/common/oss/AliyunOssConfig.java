package xyz.fusheng.test.common.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @FileName: AliyunOssConfig
 * @Author: code-fusheng
 * @Date: 2021/8/2 下午3:08
 * @Version: 1.0
 * @Description: 阿里云OSS配置类
 */

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {

    private String bucketName;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

}
