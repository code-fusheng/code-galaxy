package xyz.fusheng.exam.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.PaperMapper;
import xyz.fusheng.exam.service.PaperService;
@Service
public class PaperServiceImpl implements PaperService{

    @Resource
    private PaperMapper paperMapper;

}
