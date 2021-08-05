package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.vo.PaperVo;
import xyz.fusheng.exam.model.entity.Paper;

import java.util.List;

@Mapper
public interface PaperMapper extends BaseMapper<Paper> {
    /**
     * 统计试题在试卷中的引用次数
     *
     * @param questionId
     * @return
     */
    int checkQuestionIsUsedByPaper(Long questionId);

    /**
     * 分页查询试卷
     * @param page
     * @return
     */
    List<PaperVo> getByPage(Page<PaperVo> page);

    /**
     * 统计分页试卷总数
     * @param page
     * @return
     */
    int getCountByPage(Page<PaperVo> page);

    /**
     * 获取试卷基础信息
     * @param paperId
     * @return
     */
    PaperVo getPaperBaseInfoById(Long paperId);

    /**
     * 获取考试选用的试卷信息
     * @param examId
     * @return
     */
    List<PaperVo> getPaperVoListByExamId(Long examId);
}
