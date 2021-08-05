package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.entity.Exam;
import xyz.fusheng.exam.model.vo.ExamVo;

import java.util.List;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {

    /**
     * 分页或者考试信息
     * @param page
     * @return
     */
    List<ExamVo> getByPage(PageData<ExamVo> page);

    int getCountByPage(PageData<ExamVo> page);
}
