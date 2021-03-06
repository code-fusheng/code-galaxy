package xyz.fusheng.sys.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.sys.model.entity.DictType;
import xyz.fusheng.sys.model.vo.DictTypeVo;

import java.util.List;

@Mapper
public interface DictTypeMapper extends BaseMapper<DictType> {

    /**
     * 分页查询字典类型视图
     * @param page
     * @return
     */
    List<DictTypeVo> getByPage(PageData<DictTypeVo> page);

    /**
     * 统计字典类型总数
     * @param page
     * @return
     */
    int getCountByPage(PageData<DictTypeVo> page);

}
