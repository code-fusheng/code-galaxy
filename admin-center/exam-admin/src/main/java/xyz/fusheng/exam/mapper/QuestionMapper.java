package xyz.fusheng.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.entity.Question;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 获取试题所在题库的id集合
     * @param questionId
     * @return
     */
    List<Long> getRepositoryIdsByQuestionId(Long questionId);

}