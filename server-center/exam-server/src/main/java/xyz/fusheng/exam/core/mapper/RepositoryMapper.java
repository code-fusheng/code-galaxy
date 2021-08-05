package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.exam.model.entity.Repository;
import xyz.fusheng.exam.model.vo.RepositoryVo;

import java.util.List;

@Mapper
public interface RepositoryMapper extends BaseMapper<Repository> {

    /**
     * 保存题库与试题关系
     *
     * @param repositoryId
     * @param questionId
     */
    void saveRefForRepositoryAndQuestion(@Param("repositoryId") Long repositoryId, @Param("questionId") Long questionId);

    /**
     * 根据试题Id删除题库与试题的关联关系
     *
     * @param repositoryId
     * @param questionId
     */
    void deleteRefForRepositoryAndQuestion(@Param("repositoryId") Long repositoryId, @Param("questionId") Long questionId);

    /**
     * 分页查询题库列表
     * @param page
     * @return
     */
    List<RepositoryVo> getByPage(Page<RepositoryVo> page);

    /**
     * 统计分页题库总数
     * @param page
     * @return
     */
    int getCountByPage(Page<RepositoryVo> page);

    /**
     * 查询当前试题是否修改归属题库
     *
     * @param repositoryId
     * @param questionId
     */
    int checkIsChangeRepository(@Param("repositoryId") Long repositoryId, @Param("questionId") Long questionId);
}
