package xyz.fusheng.bill.core.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fusheng.bill.core.mapper.CategoryMapper;
import xyz.fusheng.bill.core.mapper.TransRecordMapper;
import xyz.fusheng.bill.core.service.TransRecordService;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.bill.dto.TransRecordDto;
import xyz.fusheng.core.model.bill.entity.Category;
import xyz.fusheng.core.model.bill.entity.TransRecord;
import xyz.fusheng.core.model.bill.vo.TransRecordVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TransRecordServiceImpl implements TransRecordService {

    @Resource
    private TransRecordMapper transRecordMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Page<TransRecordVo> pageTransRecord(Page<TransRecordVo> page) {
        List<TransRecordVo> transRecordVoList = transRecordMapper.pageTransRecordList(page);
        page.setList(transRecordVoList);
        int totalCount = transRecordMapper.countTransRecordPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTransRecord(TransRecordDto transRecordDto) {
        TransRecord transRecord = new TransRecord();
        BeanUtils.copyProperties(transRecordDto, transRecord);
        transRecordMapper.insert(transRecord);
        Category category = categoryMapper.selectById(transRecordDto.getTransCategory());
        if (ObjectUtils.isEmpty(category)) { throw new BusinessException(ResultEnums.BUSINESS_ERROR.getCode(), "消费类型异常!"); }
        category.setItemCount(category.getItemCount() + 1);
        categoryMapper.updateById(category);
    }
}