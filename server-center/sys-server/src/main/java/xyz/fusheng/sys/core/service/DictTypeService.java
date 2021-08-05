package xyz.fusheng.sys.core.service;

import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.sys.model.dto.DictTypeDto;
import xyz.fusheng.sys.model.entity.DictType;
import xyz.fusheng.sys.model.vo.DictTypeVo;

public interface DictTypeService {

    /**
     * 添加字典类型
     * @param dictTypeDto
     */
    void saveDictType(DictTypeDto dictTypeDto);

    /**
     * 批量删除字典类型根据Id
     * @param dictIds
     */
    void deleteDictType(Long[] dictIds);

    /**
     * 修改字典类型
     * @param dictTypeDto
     */
    void updateDictType(DictTypeDto dictTypeDto);

    /**
     * 根据Id获取字典类型详情
     * @param dictTypeId
     * @return
     */
    DictType infoDictType(Long dictTypeId);

    /**
     * 分页查询字典类型列表
     * @param page
     * @return
     */
    PageData<DictTypeVo> pageDictType(PageData<DictTypeVo> page);
}
