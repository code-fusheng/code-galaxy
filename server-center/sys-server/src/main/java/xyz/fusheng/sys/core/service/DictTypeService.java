package xyz.fusheng.sys.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.DictTypeDto;
import xyz.fusheng.core.model.entity.DictType;
import xyz.fusheng.core.model.vo.DictTypeVo;

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
    void deleteDictTypedByIds(Long[] dictIds);

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
    DictType getDictTypeById(Long dictTypeId);

    /**
     * 分页查询字典类型列表
     * @param page
     * @return
     */
    Page<DictTypeVo> getDictTypeByPage(Page<DictTypeVo> page);
}
