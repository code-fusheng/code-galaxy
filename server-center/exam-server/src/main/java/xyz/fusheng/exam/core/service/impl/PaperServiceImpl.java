package xyz.fusheng.exam.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.exam.core.mapper.PaperMapper;
import xyz.fusheng.exam.core.service.PaperService;
import xyz.fusheng.exam.model.dto.PaperDto;
import xyz.fusheng.exam.model.entity.Paper;
import xyz.fusheng.exam.model.vo.PaperVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    private static final Logger logger = LoggerFactory.getLogger(PaperService.class);

    @Resource
    private PaperMapper paperMapper;

    /**
     * 添加试卷
     * @param paperDto
     */
    @Override
    public void savePaper(PaperDto paperDto) {
        Paper paper = new Paper();
        BeanUtils.copyProperties(paperDto, paper);
        logger.info("添加试卷参数预览:{}", paper);
        paperMapper.insert(paper);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<PaperVo> getPaperByPage(Page<PaperVo> page) {
        // 查询数据
        List<PaperVo> paperVoList = paperMapper.getByPage(page);
        page.setList(paperVoList);
        // 统计总数
        int totalCount = paperMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public PaperVo getPaperBaseInfoById(Long paperId) {
        PaperVo paperVo = paperMapper.getPaperBaseInfoById(paperId);
        return paperVo;
    }

    @Override
    public List<PaperVo> getPaperVoListByExamId(Long examId) {
        List<PaperVo> paperVoList = paperMapper.getPaperVoListByExamId(examId);
        return paperVoList;
    }
}

