package xyz.fusheng.exam.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.OptionMapper;
import xyz.fusheng.exam.service.OptionService;
@Service
public class OptionServiceImpl implements OptionService{

    @Resource
    private OptionMapper optionMapper;

}
