package xyz.fusheng.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.mapper.PaperMapper;
import xyz.fusheng.service.PaperService;

@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperMapper paperMapper;

}

