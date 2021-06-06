package xyz.fusheng.admin.exam.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.admin.exam.core.mapper.ExamMapper;
import xyz.fusheng.admin.exam.core.service.ExamService;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.ExamDto;
import xyz.fusheng.core.model.entity.Exam;
import xyz.fusheng.core.model.vo.ExamVo;

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

