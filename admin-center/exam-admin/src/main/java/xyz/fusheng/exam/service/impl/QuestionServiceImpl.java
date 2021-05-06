package xyz.fusheng.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.exam.mapper.OptionMapper;
import xyz.fusheng.exam.mapper.PaperMapper;
import xyz.fusheng.exam.mapper.QuestionMapper;
import xyz.fusheng.exam.mapper.RepositoryMapper;
import xyz.fusheng.exam.service.QuestionService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.QuestionDto;
import xyz.fusheng.model.entity.Option;
import xyz.fusheng.model.entity.Question;
import xyz.fusheng.model.entity.Repository;
import xyz.fusheng.model.vo.QuestionVo;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private OptionMapper optionMapper;

    @Resource
    private PaperMapper paperMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuestion(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        logger.info("插入试题参数预览:{}", question);
        // 插入试题
        questionMapper.insert(question);
        // 保存试题与题库的关系
        // 通过 Mybatis Plus 插入数据会将主键Id回填到实体对象中
        repositoryMapper.saveRefForRepositoryAndQuestion(questionDto.getRepositoryId(), question.getQuestionId());
        // 更新题库试题数
        Repository repository = repositoryMapper.selectById(questionDto.getRepositoryId());
        repository.setQuestionCount(repository.getQuestionCount() + 1);
        repositoryMapper.updateById(repository);
        logger.info("更新题库详情预览:{}", repository);
        // 判断试题传输对象中选项列表是否为空，不为空时批量插入选项
        if (questionDto.getOptionList() != null && questionDto.getOptionList().size() > 0) {
            List<Option> optionList = questionDto.getOptionList();
            optionList.forEach(option -> {
                option.setQuestionId(questionDto.getQuestionId());
                optionMapper.insert(option);
            });
        }
    }

    @Override
    public boolean checkQuestionIsUsedByPaper(Long[] questionIds) {
        List<Long> questionIdList = Arrays.asList(questionIds);
        for (Long questionId : questionIdList) {
            int usedCount = paperMapper.checkQuestionIsUsedByPaper(questionId);
            if (usedCount > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 逻辑删除试题
     * @param questionIds
     */
    @Override
    public void deleteQuestionByIds(Long[] questionIds) {
        List<Long> questionIdList = Arrays.asList(questionIds);
        for (Long questionId : questionIdList) {
            // 删除试题
            questionMapper.deleteById(questionId);
            // 删除选项答案 试题属于逻辑删除操作，答案仅仅通过试题来获取，故无需删除答案
            // 删除试题后需要处理试题与题库的关系
            List<Long> repositoryIds = questionMapper.getRepositoryIdsByQuestionId(questionId);
            for (Long repositoryId : repositoryIds) {
                // 删除题库试题中间表
                repositoryMapper.deleteRefForRepositoryAndQuestion(repositoryId, questionId);
                // 更新试题数根据题库编号
                Repository repository = repositoryMapper.selectById(repositoryId);
                repository.setQuestionCount(repository.getQuestionCount() - 1);
                repositoryMapper.updateById(repository);
            }
        }
        if (questionIdList.size() > 0) {
            questionMapper.deleteBatchIds(questionIdList);
        }
    }

    @Override
    public Page<QuestionVo> getRepositoryByPage(Page<QuestionVo> page) {
        // 查询数据
        List<QuestionVo> questionVoList = questionMapper.getByPage(page);
        page.setList(questionVoList);
        // 统计总数
        int totalCount = questionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 获取试题与选项连同答案
     * @param questionId
     * @return
     */
    @Override
    public QuestionVo getQuestionWithOptionsAndAnswersById(Long questionId) {
        // 先获取试题详情(含题库ID与名称)
        QuestionVo questionVo = questionMapper.getQuestionVoById(questionId);
        // 获取试题选项连同答案
        List<Option> optionList = optionMapper.selectList(new QueryWrapper<Option>().lambda()
                .eq(Option::getQuestionId, questionId)
                .eq(Option::getIsEnabled, StateEnums.ENABLED.getCode()));
        questionVo.setOptionList(optionList);
        return questionVo;
    }
}
