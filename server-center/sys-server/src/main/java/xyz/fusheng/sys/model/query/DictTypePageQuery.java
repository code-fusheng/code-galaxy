package xyz.fusheng.sys.model.query;

import lombok.Data;
import xyz.fusheng.core.model.base.BaseQuery;
import xyz.fusheng.sys.model.vo.DictTypeVo;

/**
 * @FileName: DictTypePageQuery
 * @Author: code-fusheng
 * @Date: 2021/8/5 下午12:47
 * @Version: 1.0
 * @Description:
 */

@Data
public class DictTypePageQuery extends BaseQuery<DictTypeVo> {

    private String dictName;

    private String dictType;

    private String creatorName;

}
