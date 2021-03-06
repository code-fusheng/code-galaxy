package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.entity.Question;
import xyz.fusheng.exam.model.vo.QuestionVo;

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
    List<QuestionVo> getByPage(PageData<QuestionVo> page);

    /**
     * 统计分页试题总数
     * @param page
     * @return
     */
    int getCountByPage(PageData<QuestionVo> page);

    /**
     * 获取试题视图对象(包含题库名称)
     * @param questionId
     * @return
     */
    QuestionVo getQuestionVoById(Long questionId);

    /**
     *
     * @param page
     * @return
     */
    List<QuestionVo> getSimpleQuestionByPage(PageData<QuestionVo> page);

    int getSimpleCountByPage(PageData<QuestionVo> page);

    List<QuestionVo> getAllQuestionAndOptionsWithAnswers();
}
