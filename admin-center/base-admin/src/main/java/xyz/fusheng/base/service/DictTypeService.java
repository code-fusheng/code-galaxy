package xyz.fusheng.base.service;

import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.DictTypeDto;
import xyz.fusheng.model.vo.DictTypeVo;

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
    DictTypeVo getDictTypeById(Long dictTypeId);

    /**
     * 分页查询字典类型列表
     * @param page
     * @return
     */
    Page<DictTypeVo> getDictTypeByPage(Page<DictTypeVo> page);
}
