package xyz.fusheng.core.model.query;

import lombok.Data;
import xyz.fusheng.core.model.base.BaseQuery;

/**
 * @FileName: RepositoryQuery
 * @Author: code-fusheng
 * @Date: 2021/4/22 10:44 下午
 * @Version: 1.0
 * @Description:
 */

@Data
public class RepositoryQuery extends BaseQuery {

    private String repositoryName;

}
