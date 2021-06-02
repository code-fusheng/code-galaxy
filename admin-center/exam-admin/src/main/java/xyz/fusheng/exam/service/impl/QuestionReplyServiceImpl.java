package xyz.fusheng.exam.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.QuestionReplyMapper;
import xyz.fusheng.exam.service.QuestionReplyService;
@Service
public class QuestionReplyServiceImpl implements QuestionReplyService{

    @Resource
    private QuestionReplyMapper questionReplyMapper;

}
