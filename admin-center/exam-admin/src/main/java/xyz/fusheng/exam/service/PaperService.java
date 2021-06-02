package xyz.fusheng.exam.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.PaperDto;
import xyz.fusheng.model.vo.PaperVo;

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
    Page<PaperVo> getPaperByPage(Page<PaperVo> page);

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

