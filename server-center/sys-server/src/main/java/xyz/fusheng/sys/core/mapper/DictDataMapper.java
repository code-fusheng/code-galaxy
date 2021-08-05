package xyz.fusheng.sys.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.sys.model.entity.DictData;
import xyz.fusheng.sys.model.vo.DictDataVo;

import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 根据字典类型获取字典列表
     * @param dictType
     * @return
     */
    List<DictDataVo> listDictData(String dictType);

    /**
     * 分页查询字典数据视图
     * @param page
     * @return
     */
    List<DictDataVo> getByPage(PageData<DictDataVo> page);

    /**
     * 统计字典数据分页总数
     * @param page
     * @return
     */
    int getCountByPage(PageData<DictDataVo> page);
}
