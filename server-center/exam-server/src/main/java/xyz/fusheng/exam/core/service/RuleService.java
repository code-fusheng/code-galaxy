package xyz.fusheng.exam.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.dto.RuleDto;
import xyz.fusheng.exam.model.entity.Rule;
import xyz.fusheng.exam.model.vo.RuleVo;

import java.util.List;

public interface RuleService {

    /**
     * 添加组卷规则
     * @param ruleDto
     */
    void saveRule(RuleDto ruleDto);

    /**
     * 分页查询规则详情
     * @param page
     * @return
     */
    PageData<RuleVo> getRuleByPage(PageData<RuleVo> page);

    /**
     * 获取规则信息
     * @param ruleId
     * @return
     */
    RuleVo getRuleById(Long ruleId);

    /**
     * 获取所有规则
     * @return
     */
    List<Rule> getRuleList();
}

