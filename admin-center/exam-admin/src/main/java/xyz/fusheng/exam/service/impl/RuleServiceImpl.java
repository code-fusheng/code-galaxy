package xyz.fusheng.exam.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.RuleMapper;
import xyz.fusheng.exam.service.RuleService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.RuleDto;
import xyz.fusheng.model.entity.Rule;
import xyz.fusheng.model.vo.RuleVo;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Resource
    private RuleMapper ruleMapper;

    /**
     * 试卷规则
     * @param ruleDto
     */
    @Override
    public void saveRule(RuleDto ruleDto) {
        Rule rule = new Rule();
        BeanUtils.copyProperties(ruleDto, rule);
        ruleMapper.insert(rule);
    }

    /**
     * 分页查询试卷规则
     * @param page
     * @return
     */
    @Override
    public Page<RuleVo> getRuleByPage(Page<RuleVo> page) {
        // 查询数据
        List<RuleVo> ruleVoList = ruleMapper.getByPage(page);
        page.setList(ruleVoList);
        // 统计分页总数
        int totalCount = ruleMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}

