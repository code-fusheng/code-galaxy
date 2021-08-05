package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.vo.RepositoryVo;
import xyz.fusheng.exam.core.mapper.RepositoryMapper;
import xyz.fusheng.exam.core.service.RepositoryService;
import xyz.fusheng.exam.model.dto.RepositoryDto;
import xyz.fusheng.exam.model.entity.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

    @Override
    public void saveRepository(RepositoryDto repositoryDto) {
        Repository repository = new Repository();
        BeanUtils.copyProperties(repositoryDto, repository);
        repositoryMapper.insert(repository);
    }

    @Override
    public RepositoryVo getRepositoryById(Long repositoryId) {
        RepositoryVo repositoryVo = new RepositoryVo();
        Repository repository = repositoryMapper.selectById(repositoryId);
        BeanUtils.copyProperties(repository, repositoryVo);
        return repositoryVo;
    }

    @Override
    public void deleteRepositoryIds(Long[] repositoryIds) {
        List<Long> ids = Arrays.asList(repositoryIds);
        if (ids.size() > 0) {
            repositoryMapper.deleteBatchIds(ids);
        }
    }

    @Override
    public void updateRepository(RepositoryDto repositoryDto) {
        Repository repository = new Repository();
        BeanUtils.copyProperties(repositoryDto, repository);
        repositoryMapper.updateById(repository);
    }

    @Override
    public Page<RepositoryVo> getRepositoryByPage(Page<RepositoryVo> page) {
        List<RepositoryVo> repositoryVoList = repositoryMapper.getByPage(page);
        page.setList(repositoryVoList);
        int totalCount = repositoryMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Repository> getRepositoryList() {
        List<Repository> repositoryList = repositoryMapper.selectList(new QueryWrapper<Repository>().lambda()
                .select(Repository::getRepositoryId, Repository::getRepositoryName, Repository::getQuestionCount)
                .eq(Repository::getIsEnabled, StateEnums.ENABLED.getCode())
                .orderByDesc(Repository::getQuestionCount));
        return repositoryList;
    }

}
