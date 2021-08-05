package xyz.fusheng.exam.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.dto.ExamDto;
import xyz.fusheng.exam.model.vo.ExamVo;


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
    PageData<ExamVo> getExamVoByPage(PageData<ExamVo> page);
}

