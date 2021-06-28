package xyz.fusheng.admin.exam.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xyz.fusheng.admin.exam.core.mapper.ExamMapper;
import xyz.fusheng.admin.exam.core.service.ExamService;
import xyz.fusheng.admin.exam.core.service.QuestionService;
import xyz.fusheng.admin.exam.core.service.SearchService;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.model.entity.Exam;
import xyz.fusheng.core.model.entity.Question;
import xyz.fusheng.core.model.vo.QuestionVo;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Policy;
import java.util.List;

/**
 * @FileName: SearchServiceImpl
 * @Author: code-fusheng
 * @Date: 2021/6/15 3:53 下午
 * @Version: 1.0
 * @Description:
 */

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Resource
    private QuestionService questionService;

    @Override
    public void newSimpleIndex(String index) {
        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest(index);
        // Es客户端执行创建索引请求,获取响应
        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            logger.info("ES客户端创建索引请求结果:{}", createIndexResponse.toString());
        } catch (IOException e) {
            logger.error("ES客户端创建索引异常:", e);
        }
    }

    @Override
    public void bulkSync() {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        List<QuestionVo> questionVoList = questionService.getAllQuestionAndOptionsWithAnswers();
        questionVoList.forEach(questionVo -> {
            logger.info("同步试题数据预览:{}", questionVo);
            bulkRequest.add(new IndexRequest("test-question-index")
                        .type("_doc")
                        .id("" + (questionVo.getQuestionId()))
                        .source(JSON.toJSONString(questionVo), XContentType.JSON));
        });
        try {
            BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            logger.info("ES客户端同步试题请求结果:{}", response.toString());
        } catch (IOException e) {
            logger.error("ES客户端同步试题异常:", e);
        }
    }
}
