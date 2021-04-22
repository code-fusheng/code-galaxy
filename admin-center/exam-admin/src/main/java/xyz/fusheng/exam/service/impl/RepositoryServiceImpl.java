package xyz.fusheng.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.mapper.RepositoryMapper;
import xyz.fusheng.exam.service.RepositoryService;
import xyz.fusheng.model.dto.RepositoryDto;
import xyz.fusheng.model.entity.Repository;
import xyz.fusheng.model.query.RepositoryQuery;
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
    public IPage<Repository> getRepositoryByPage(RepositoryQuery queryPage) {
        IPage<Repository> pageInfo = repositoryMapper.selectPage(queryPage.getPage(), new QueryWrapper<Repository>().lambda()
                .like(StringUtils.isNotBlank(queryPage.getRepositoryName()), Repository::getRepositoryName, queryPage.getRepositoryName())
                .between(StringUtils.isNotBlank(queryPage.getBeginTime()) && StringUtils.isNotBlank(queryPage.getEndTime()),
                        Repository::getCreatedTime, queryPage.getBeginTime(), queryPage.getEndTime())
                .eq(Repository::getIsEnabled, queryPage.getIsEnabled())
                .last("order by " + queryPage.getSortColumn() + " " + queryPage.getSortMethod())
        );
        return pageInfo;
    }


}
