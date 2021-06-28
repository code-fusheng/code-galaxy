package xyz.fusheng.admin.exam.core.service;

import xyz.fusheng.core.model.vo.ResultVo;

/**
 * @FileName: SearchService
 * @Author: code-fusheng
 * @Date: 2021/6/15 3:53 下午
 * @Version: 1.0
 * @Description:
 */

public interface SearchService {

    void newSimpleIndex(String index);

    void bulkSync();
}
