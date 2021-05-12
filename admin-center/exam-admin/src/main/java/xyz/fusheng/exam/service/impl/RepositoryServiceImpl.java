package xyz.fusheng.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.exam.mapper.RepositoryMapper;
import xyz.fusheng.exam.service.RepositoryService;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.RepositoryDto;
import xyz.fusheng.model.entity.Repository;
import xyz.fusheng.model.query.RepositoryQuery;
import xyz.fusheng.model.vo.DictDataVo;
import xyz.fusheng.model.vo.RepositoryVo;
import xyz.fusheng.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService{

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
