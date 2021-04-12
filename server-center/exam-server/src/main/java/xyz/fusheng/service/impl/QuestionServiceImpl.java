package xyz.fusheng.service.impl;

import org.springframework.stereotype.Service;
import xyz.fusheng.mapper.QuestionMapper;
import xyz.fusheng.service.QuestionService;

import javax.annotation.Resource;

/**
 * @FileName: QuestionServiceImpl
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:01 上午
 * @Version: 1.0
 * @Description:
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

}
