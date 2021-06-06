package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.entity.Exam;
import xyz.fusheng.model.vo.ExamVo;

import java.util.List;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {

    /**
     * 分页或者考试信息
     * @param page
     * @return
     */
    List<ExamVo> getByPage(Page<ExamVo> page);

    int getCountByPage(Page<ExamVo> page);
}
