package xyz.fusheng.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.exam.mapper.OptionMapper;
import xyz.fusheng.exam.mapper.QuestionMapper;
import xyz.fusheng.exam.service.OptionService;
import xyz.fusheng.exception.BusinessException;
import xyz.fusheng.model.dto.OptionDto;
import xyz.fusheng.model.entity.Option;
import xyz.fusheng.model.entity.Question;

@Service
public class OptionServiceImpl implements OptionService{

    @Resource
    private OptionMapper optionMapper;

    @Resource
    private QuestionMapper questionMapper;

    /**
     * 添加试题选项
     * @param optionDto
     */
    @Override
    public void saveOption(OptionDto optionDto) {
        // 校验选项所在试题是否异常
        Question question = questionMapper.selectOne(new QueryWrapper<Question>().lambda()
                .eq(Question::getQuestionId, optionDto.getQuestionId())
                .eq(Question::getIsEnabled, StateEnums.ENABLED.getCode()));
        if (ObjectUtils.isEmpty(question)) {
            throw new BusinessException(ResultEnums.BUSINESS_ERROR.getCode(), "选项所在目标试题信息异常!");
        }
        Option option = new Option();
        BeanUtils.copyProperties(optionDto, option);
        optionMapper.insert(option);
    }
}
