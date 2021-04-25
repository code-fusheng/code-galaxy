package xyz.fusheng.exam.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.PaperRuleMapper;
import xyz.fusheng.exam.service.PaperRuleService;
@Service
public class PaperRuleServiceImpl implements PaperRuleService{

    @Resource
    private PaperRuleMapper paperRuleMapper;

}
