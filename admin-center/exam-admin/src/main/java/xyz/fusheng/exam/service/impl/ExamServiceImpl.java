package xyz.fusheng.exam.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.ExamMapper;
import xyz.fusheng.exam.service.ExamService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.ExamDto;
import xyz.fusheng.model.entity.Exam;
import xyz.fusheng.model.vo.ExamVo;

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
    public Page<ExamVo> getExamVoByPage(Page<ExamVo> page) {
        List<ExamVo> examVoList = examMapper.getByPage(page);
        page.setList(examVoList);
        int totalCount = examMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}

