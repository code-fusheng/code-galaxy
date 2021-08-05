package xyz.fusheng.exam.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.dto.PaperDto;
import xyz.fusheng.exam.model.vo.PaperVo;

import java.util.List;

public interface PaperService {

    /**
     * 添加试卷
     * @param paperDto
     */
    void savePaper(PaperDto paperDto);

    /**
     * 分页查询试卷列表
     * @param page
     * @return
     */
    PageData<PaperVo> getPaperByPage(PageData<PaperVo> page);

    /**
     * 获取试卷基础信息
     * @param paperId
     * @return
     */
    PaperVo getPaperBaseInfoById(Long paperId);

    /**
     * 获取考试选用的试卷信息列表
     * @param examId
     * @return
     */
    List<PaperVo> getPaperVoListByExamId(Long examId);
}

