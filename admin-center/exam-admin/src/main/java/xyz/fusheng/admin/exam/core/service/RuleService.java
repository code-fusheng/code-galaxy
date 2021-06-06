package xyz.fusheng.admin.exam.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.RuleDto;
import xyz.fusheng.core.model.entity.Rule;
import xyz.fusheng.core.model.vo.RuleVo;

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
    Page<RuleVo> getRuleByPage(Page<RuleVo> page);

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

