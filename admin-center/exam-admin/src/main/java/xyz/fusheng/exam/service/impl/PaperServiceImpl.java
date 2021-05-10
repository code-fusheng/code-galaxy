package xyz.fusheng.exam.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.PaperMapper;
import xyz.fusheng.exam.service.PaperService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.PaperDto;
import xyz.fusheng.model.entity.Paper;
import xyz.fusheng.model.vo.PaperVo;

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
}

