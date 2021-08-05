package xyz.fusheng.sys.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.sys.model.dto.DictDataDto;
import xyz.fusheng.sys.model.vo.DictDataVo;

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
    void deleteDictData(Long[] dictCodes);

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
    DictDataVo infoDictData(Long dictDataCode);

    /**
     * 根据字典类型获取字典数据列表
     * @param dictType
     * @return
     */
    List<DictDataVo> listDictData(String dictType);

    /**
     * 分页查询字典数据列表
     * @param page
     * @return
     */
    PageData<DictDataVo> pageDictData(PageData<DictDataVo> page);
}
