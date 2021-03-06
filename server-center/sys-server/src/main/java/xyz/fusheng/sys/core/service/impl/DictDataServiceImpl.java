package xyz.fusheng.sys.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.enums.StateEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.utils.StringUtils;
import xyz.fusheng.sys.core.mapper.DictDataMapper;
import xyz.fusheng.sys.core.mapper.DictTypeMapper;
import xyz.fusheng.sys.core.service.DictDataService;
import xyz.fusheng.sys.model.dto.DictDataDto;
import xyz.fusheng.sys.model.entity.DictData;
import xyz.fusheng.sys.model.entity.DictType;
import xyz.fusheng.sys.model.vo.DictDataVo;

import javax.annotation.Resource;
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
    public void deleteDictData(Long[] dictCodes) {
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
    public DictDataVo infoDictData(Long dictDataCode) {
        DictData dictData = dictDataMapper.selectOne(new QueryWrapper<DictData>().lambda()
                .eq(DictData::getDictCode, dictDataCode)
                .eq(DictData::getIsEnabled, StateEnums.ENABLED.getCode()));
        DictDataVo dictDataVo = new DictDataVo();
        if (ObjectUtils.isEmpty(dictData)) {
            throw new BusinessException("操作失败: 数据异常!");
        }
        BeanUtils.copyProperties(dictData, dictDataVo);
        return dictDataVo;
    }

    @Override
    public List<DictDataVo> listDictData(String dictType) {
        List<DictDataVo> dictDataList = dictDataMapper.listDictData(dictType);
        return dictDataList;
    }

    @Override
    public PageData<DictDataVo> pageDictData(PageData<DictDataVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 字典排序、字典标签、创建时间、更新时间
            String[] sortColumns = {"dict_sort", "dict_label", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                throw new BusinessException(ResultEnum.ERROR.getCode(), "参数错误!");
            }
        }
        List<DictDataVo> dictDataVoList = dictDataMapper.getByPage(page);
        page.setList(dictDataVoList);
        int totalCount = dictDataMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
