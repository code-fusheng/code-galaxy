package xyz.fusheng.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.entity.DictData;
import xyz.fusheng.model.vo.DictDataVo;

import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 根据字典类型获取字典列表
     * @param dictType
     * @return
     */
    List<DictDataVo> listDictDataByDictType(String dictType);

    /**
     * 分页查询字典数据视图
     * @param page
     * @return
     */
    List<DictDataVo> getByPage(Page<DictDataVo> page);

    /**
     * 统计字典数据分页总数
     * @param page
     * @return
     */
    int getCountByPage(Page<DictDataVo> page);
}