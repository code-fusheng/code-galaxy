package xyz.fusheng.exam.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @FileName: ElasticsearchConfig
 * @Author: code-fusheng
 * @Date: 2021/6/15 3:49 下午
 * @Version: 1.0
 * @Description: Es配置类
 */

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("175.24.45.179", 9200, "http")
                )
        );
        return client;
    }

}
