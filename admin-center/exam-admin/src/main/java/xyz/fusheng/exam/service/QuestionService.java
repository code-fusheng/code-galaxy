package xyz.fusheng.exam.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.QuestionDto;
import xyz.fusheng.model.vo.QuestionVo;

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
    Page<QuestionVo> getRepositoryByPage(Page<QuestionVo> page);

    /**
     * 获取试题与选项连同答案
     * @param questionId
     * @return
     */
    QuestionVo getQuestionWithOptionsAndAnswersById(Long questionId);
}
