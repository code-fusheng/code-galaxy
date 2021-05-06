package xyz.fusheng.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.entity.Question;
import xyz.fusheng.model.vo.QuestionVo;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 获取试题所在题库的id集合
     * @param questionId
     * @return
     */
    List<Long> getRepositoryIdsByQuestionId(Long questionId);

    /**
     * 分页查询试题
     * @param page
     * @return
     */
    List<QuestionVo> getByPage(Page<QuestionVo> page);

    /**
     * 统计分页试题总数
     * @param page
     * @return
     */
    int getCountByPage(Page<QuestionVo> page);

    /**
     * 获取试题视图对象(包含题库名称)
     * @param questionId
     * @return
     */
    QuestionVo getQuestionVoById(Long questionId);
}
