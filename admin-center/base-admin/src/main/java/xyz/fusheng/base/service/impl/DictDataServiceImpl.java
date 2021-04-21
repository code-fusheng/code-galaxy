package xyz.fusheng.base.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.base.mapper.DictDataMapper;
import xyz.fusheng.base.service.DictDataService;


@Service
public class DictDataServiceImpl implements DictDataService {

    @Resource
    private DictDataMapper dictDataMapper;

}
