package xyz.fusheng.exam.core.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.ExamDto;
import xyz.fusheng.model.vo.ExamVo;

public interface ExamService {

    /**
     * 添加考试
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

