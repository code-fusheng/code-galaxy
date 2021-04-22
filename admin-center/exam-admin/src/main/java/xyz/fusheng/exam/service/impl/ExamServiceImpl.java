package xyz.fusheng.exam.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.ExamMapper;
import xyz.fusheng.exam.service.ExamService;
@Service
public class ExamServiceImpl implements ExamService{

    @Resource
    private ExamMapper examMapper;

}
