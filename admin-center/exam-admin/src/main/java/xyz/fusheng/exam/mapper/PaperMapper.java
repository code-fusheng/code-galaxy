package xyz.fusheng.exam.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperMapper {
    /**
     * 统计试题在试卷中的引用次数
     *
     * @param questionId
     * @return
     */
    int checkQuestionIsUsedByPaper(Long questionId);
}