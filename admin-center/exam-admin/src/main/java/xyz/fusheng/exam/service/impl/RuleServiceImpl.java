package xyz.fusheng.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.exam.mapper.RuleMapper;
import xyz.fusheng.exam.service.RuleService;
import xyz.fusheng.exception.BusinessException;
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

    /**
     * 获取规则信息
     * @param ruleId
     * @return
     */
    @Override
    public RuleVo getRuleById(Long ruleId) {
        RuleVo ruleVo = new RuleVo();
        Rule rule = ruleMapper.selectById(ruleId);
        if (ObjectUtils.isEmpty(rule)) {
            throw new BusinessException(ResultEnums.BUSINESS_ERROR.getCode(), "规则信息查询异常!");
        }
        BeanUtils.copyProperties(rule, ruleVo);
        return ruleVo;
    }

    @Override
    public List<Rule> getRuleList() {
        // TODO 后续添加根据引用次数排序
        List<Rule> ruleList = ruleMapper.selectList(new QueryWrapper<Rule>().lambda()
                .eq(Rule::getIsEnabled, StateEnums.ENABLED.getCode()));
        return ruleList;
    }
}

