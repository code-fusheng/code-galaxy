package xyz.fusheng.admin.exam.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.ExamDto;
import xyz.fusheng.core.model.vo.ExamVo;

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

