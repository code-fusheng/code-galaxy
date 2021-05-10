package xyz.fusheng.exam.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.RuleDto;
import xyz.fusheng.model.vo.RuleVo;

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
    Page<RuleVo> getRuleByPage(Page<RuleVo> page);

    /**
     * 获取规则信息
     * @param ruleId
     * @return
     */
    RuleVo getRuleById(Long ruleId);
}

