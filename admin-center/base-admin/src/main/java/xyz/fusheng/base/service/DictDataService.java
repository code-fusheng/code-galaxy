package xyz.fusheng.base.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.DictDataDto;
import xyz.fusheng.model.vo.DictDataVo;
import xyz.fusheng.model.vo.DictTypeVo;

import java.util.List;

public interface DictDataService{

    /**
     * 添加字典数据
     * @param dictDataDto
     */
    void saveDictData(DictDataDto dictDataDto);

    /**
     * 批量删除字典数据根据Ids
     * @param dictCodes
     */
    void deleteDictDataByCodes(Long[] dictCodes);

    /**
     * 修改字典数据
     * @param dictDataDto
     */
    void updateDictData(DictDataDto dictDataDto);

    /**
     * 根据Id获取字典数据详情
     * @param dictDataCode
     * @return
     */
    DictDataVo getDictDataByCode(Long dictDataCode);

    /**
     * 根据字典类型获取字典数据列表
     * @param dictType
     * @return
     */
    List<DictDataVo> listDictDataByDictType(String dictType);

    /**
     * 分页查询字典数据列表
     * @param page
     * @return
     */
    Page<DictDataVo> getDictDataByPage(Page<DictDataVo> page);
}
