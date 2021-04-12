package xyz.fusheng.service.impl;

import org.springframework.stereotype.Service;
import xyz.fusheng.mapper.RepositoryMapper;
import xyz.fusheng.service.RepositoryService;

import javax.annotation.Resource;

/**
 * @FileName: RepositoryServiceImpl
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:03 上午
 * @Version: 1.0
 * @Description:
 */

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

}
