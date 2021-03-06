package xyz.fusheng.sys.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.utils.StringUtils;
import xyz.fusheng.sys.core.mapper.DictTypeMapper;
import xyz.fusheng.sys.core.service.DictTypeService;
import xyz.fusheng.sys.model.dto.DictTypeDto;
import xyz.fusheng.sys.model.entity.DictType;
import xyz.fusheng.sys.model.vo.DictTypeVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: DictTypeServiceImpl
 * @Author: code-fusheng
 * @Date: 2021/4/12 11:06 下午
 * @Version: 1.0
 * @Description:
 */

@Service
public class DictTypeServiceImpl implements DictTypeService {

    private static final Logger logger = LoggerFactory.getLogger(DictTypeServiceImpl.class);

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public void saveDictType(DictTypeDto dictTypeDto) {
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>().lambda()
                .eq(DictType::getIsEnabled, StateEnums.ENABLED.getCode())
                .and(i -> i.eq(DictType::getDictName, dictTypeDto.getDictName())
                           .or()
                           .eq(DictType::getDictType, dictTypeDto.getDictType())));
        Assert.isTrue(ObjectUtils.isEmpty(dictType), "添加失败!字典类型【"+ dictTypeDto.getDictName() +"】已经存在");
        dictType = new DictType();
        BeanUtils.copyProperties(dictTypeDto, dictType);
        dictTypeMapper.insert(dictType);
    }

    @Override
    public void deleteDictType(Long[] dictIds) {
        List<Long> ids = Arrays.asList(dictIds);
        if (ids.size() > 0) {
            dictTypeMapper.deleteBatchIds(ids);
        }
    }

    @Override
    public void updateDictType(DictTypeDto dictTypeDto) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dictTypeDto, dictType);
        int updateResult = dictTypeMapper.updateById(dictType);
        logger.info("更新操作结果:{}, 预览:{}", updateResult, dictType);
    }

    @Override
    public DictType infoDictType(Long dictTypeId) {
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>().lambda()
                .eq(DictType::getDictId, dictTypeId)
                .eq(DictType::getIsEnabled, StateEnums.ENABLED.getCode()));
        return dictType;
    }

    @Override
    public PageData<DictTypeVo> pageDictType(PageData<DictTypeVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 字典名称、字典类型、创建时间、更新时间
            String[] sortColumns = {"dict_name", "dict_type", "created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                throw new BusinessException(ResultEnum.ERROR.getCode(), "参数错误!");
            }
        }
        // 查询数据
        List<DictTypeVo> dictTypeVoList = dictTypeMapper.getByPage(page);
        page.setList(dictTypeVoList);
        // 统计总数
        int totalCount = dictTypeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
