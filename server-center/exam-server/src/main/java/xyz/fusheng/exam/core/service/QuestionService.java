package xyz.fusheng.exam.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.dto.QuestionDto;
import xyz.fusheng.exam.model.vo.QuestionVo;

import java.util.List;

public interface QuestionService{

    /**
     * 添加试题
     * @param questionDto
     */
    void saveQuestion(QuestionDto questionDto);

    /**
     * 检查试题列表中是否存在被试卷引用的试题
     * @param questionIds
     * @return
     */
    boolean checkQuestionIsUsedByPaper(Long[] questionIds);

    /**
     * 批量删除试题
     * @param questionIds
     */
    void deleteQuestionByIds(Long[] questionIds);

    /**
     * 分页查询试题
     * @param page
     * @return
     */
    PageData<QuestionVo> getQuestionByPage(PageData<QuestionVo> page);

    /**
     * 获取试题与选项连同答案
     * @param questionId
     * @return
     */
    QuestionVo getQuestionWithOptionsAndAnswersById(Long questionId);

    /**
     * 分页获取试卷中试题与选项信息（含答案）
     * @param page
     * @return
     */
    PageData<QuestionVo> getQuestionAndOptionsWithAnswersByPage(PageData<QuestionVo> page);

    /**
     * 更新问题与选项连同答案
     * @param questionDto
     */
    void updateQuestionWithOptionsAndAnswers(QuestionDto questionDto);

    /**
     * 分页获取试卷中试题与选项信息（不含答案）
     * @param page
     * @return
     */
    PageData<QuestionVo> getQuestionAndOptionsNotWithAnswersByPage(PageData<QuestionVo> page);

    /**
     * 获取所有试题与选项以及答案列表
     * @return
     */
    List<QuestionVo> getAllQuestionAndOptionsWithAnswers();
}
