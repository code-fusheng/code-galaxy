package xyz.fusheng.exam.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.QuestionMapper;
import xyz.fusheng.exam.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService{

    @Resource
    private QuestionMapper questionMapper;

}