package xyz.fusheng.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fusheng.model.entity.Repository;

@Mapper
public interface RepositoryMapper extends BaseMapper<Repository> {

    /**
     * 保存题库与试题关系
     * @param repositoryId
     * @param questionId
     */
    void saveRefForRepositoryAndQuestion(@Param("repositoryId") Long repositoryId,@Param("questionId") Long questionId);

    /**
     * 根据试题Id删除题库与试题的关联关系
     * @param repositoryId
     * @param questionId
     */
    void deleteRefForRepositoryAndQuestion(@Param("repositoryId") Long repositoryId,@Param("questionId") Long questionId);
}