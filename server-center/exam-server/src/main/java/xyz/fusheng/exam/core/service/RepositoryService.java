package xyz.fusheng.exam.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.dto.RepositoryDto;
import xyz.fusheng.exam.model.entity.Repository;
import xyz.fusheng.exam.model.vo.RepositoryVo;

import java.util.List;

public interface RepositoryService{

    /**
     * 添加题库
     * @param repositoryDto
     */
    void saveRepository(RepositoryDto repositoryDto);

    /**
     * 获取题库详情
     * @param repositoryId
     * @return
     */
    RepositoryVo getRepositoryById(Long repositoryId);

    /**
     * 批量删除题库
     * @param repositoryIds
     */
    void deleteRepositoryIds(Long[] repositoryIds);

    /**
     * 更新题库
     * @param repositoryDto
     */
    void updateRepository(RepositoryDto repositoryDto);

    /**
     * 分页查询题库
     * @return
     */
    PageData<RepositoryVo> getRepositoryByPage(PageData<RepositoryVo> queryPage);

    /**
     * 获取所有题库列表
     * @return
     */
    List<Repository> getRepositoryList();
}
