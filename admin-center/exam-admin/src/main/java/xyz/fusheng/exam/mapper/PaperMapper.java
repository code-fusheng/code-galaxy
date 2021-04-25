package xyz.fusheng.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.entity.Paper;

@Mapper
public interface PaperMapper extends BaseMapper<Paper> {

    /**
     * 统计试题在试卷中的引用次数
     * @param questionId
     * @return
     */
    int checkQuestionIsUsedByPaper(Long questionId);

}