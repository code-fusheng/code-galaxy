package xyz.fusheng.exam.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.core.mapper.QuestionReplyMapper;
import xyz.fusheng.exam.core.service.QuestionReplyService;
@Service
public class QuestionReplyServiceImpl implements QuestionReplyService{

    @Resource
    private QuestionReplyMapper questionReplyMapper;

}
