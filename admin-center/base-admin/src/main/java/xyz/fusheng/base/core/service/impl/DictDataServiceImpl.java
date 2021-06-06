package xyz.fusheng.base.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import xyz.fusheng.base.core.mapper.DictDataMapper;
import xyz.fusheng.base.core.mapper.DictTypeMapper;
import xyz.fusheng.base.core.service.DictDataService;
import xyz.fusheng.enums.StateEnums;
import xyz.fusheng.exception.BusinessException;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.DictDataDto;
import xyz.fusheng.model.entity.DictData;
import xyz.fusheng.model.entity.DictType;
import xyz.fusheng.model.vo.DictDataVo;

import java.util.Arrays;
import java.util.List;


@Service
public class DictDataServiceImpl implements DictDataService {

    private static final Logger logger = LoggerFactory.getLogger(DictDataServiceImpl.class);

    @Resource
    private DictDataMapper dictDataMapper;

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public void saveDictData(DictDataDto dictDataDto) {
        // 先校验是否存在可用的字典类型
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>().lambda()
            .eq(DictType::getDictType, dictDataDto.getDictType())
            .eq(DictType::getIsEnabled, StateEnums.ENABLED.getCode()));
        logger.info("字典类型:{}", dictType);
        if (ObjectUtils.isEmpty(dictType)) {
            throw new BusinessException("添加失败!字典类型【" + dictDataDto.getDictType() + "】不存在");
        }
        // 去重
        DictData dictData = dictDataMapper.selectOne(new QueryWrapper<DictData>().lambda()
            .eq(DictData::getIsEnabled, StateEnums.ENABLED.getCode())
            .and(i -> i.eq(DictData::getDictLabel, dictDataDto.getDictLabel())
                       .or()
                       .eq(DictData::getDictValue, dictDataDto.getDictValue())));
        logger.info("字典数据:{}", dictData);
        if (!ObjectUtils.isEmpty(dictData)) {
            throw new BusinessException("添加失败!字典数据【" + dictData.getDictLabel() + "】已经存在");
        }
        dictData = new DictData();
        BeanUtils.copyProperties(dictDataDto, dictData);
        dictDataMapper.insert(dictData);
    }

    @Override
    public void deleteDictDataByCodes(Long[] dictCodes) {
        List<Long> codes = Arrays.asList(dictCodes);
        if (codes.size() > 0) {
            dictDataMapper.deleteBatchIds(codes);
        }
    }

    @Override
    public void updateDictData(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDto, dictData);
        int updateResult = dictDataMapper.updateById(dictData);
        logger.info("更新操作结果:{}, 预览:{}", updateResult, dictData);
    }

    @Override
    public DictDataVo getDictDataByCode(Long dictDataCode) {
        DictData dictData = dictDataMapper.selectOne(new QueryWrapper<DictData>().lambda()
                .eq(DictData::getDictCode, dictDataCode)
                .eq(DictData::getIsEnabled, StateEnums.ENABLED.getCode()));
        DictDataVo dictDataVo = new DictDataVo();
        Assert.isTrue(ObjectUtils.isEmpty(dictData), "操作提示:更新失败!");
        BeanUtils.copyProperties(dictData, dictDataVo);
        return dictDataVo;
    }

    @Override
    public List<DictDataVo> listDictDataByDictType(String dictType) {
        List<DictDataVo> dictDataList = dictDataMapper.listDictDataByDictType(dictType);
        return dictDataList;
    }

    @Override
    public Page<DictDataVo> getDictDataByPage(Page<DictDataVo> page) {
        List<DictDataVo> dictDataVoList = dictDataMapper.getByPage(page);
        page.setList(dictDataVoList);
        int totalCount = dictDataMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
