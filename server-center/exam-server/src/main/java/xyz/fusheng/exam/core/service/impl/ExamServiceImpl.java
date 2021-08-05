package xyz.fusheng.exam.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.core.mapper.ExamMapper;
import xyz.fusheng.exam.core.service.ExamService;
import xyz.fusheng.exam.model.dto.ExamDto;
import xyz.fusheng.exam.model.entity.Exam;
import xyz.fusheng.exam.model.vo.ExamVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    private ExamMapper examMapper;

    @Override
    public void saveExam(ExamDto examDto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto, exam);
        examMapper.insert(exam);
    }

    @Override
    public PageData<ExamVo> getExamVoByPage(PageData<ExamVo> page) {
        List<ExamVo> examVoList = examMapper.getByPage(page);
        page.setList(examVoList);
        int totalCount = examMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}

