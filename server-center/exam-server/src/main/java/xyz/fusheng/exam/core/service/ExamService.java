package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.model.dto.ExamDto;


public interface ExamService {

    /**
     * 添加考试
     *
     * @param examDto
     */
    void saveExam(ExamDto examDto);

    /**
     * 分页查询考试列表
     * @param page
     * @return
     */
    Page<ExamVo> getExamVoByPage(Page<ExamVo> page);
}

