package xyz.fusheng.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.mapper.ExamMapper;
import xyz.fusheng.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    private ExamMapper examMapper;

}

