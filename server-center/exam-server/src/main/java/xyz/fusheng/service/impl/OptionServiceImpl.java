package xyz.fusheng.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.mapper.OptionMapper;
import xyz.fusheng.service.OptionService;

@Service
public class OptionServiceImpl implements OptionService {

    @Resource
    private OptionMapper optionMapper;

}

