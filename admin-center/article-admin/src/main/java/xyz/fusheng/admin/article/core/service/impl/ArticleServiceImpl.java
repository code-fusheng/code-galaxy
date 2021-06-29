package xyz.fusheng.admin.article.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.admin.article.core.mapper.ArticleMapper;
import xyz.fusheng.admin.article.core.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

}

