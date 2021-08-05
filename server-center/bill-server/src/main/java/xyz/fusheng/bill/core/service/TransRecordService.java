package xyz.fusheng.bill.core.service;

import xyz.fusheng.bill.model.dto.TransRecordDto;
import xyz.fusheng.bill.model.vo.TransRecordVo;
import xyz.fusheng.core.model.base.PageData;

public interface TransRecordService{

    PageData<TransRecordVo> pageTransRecord(PageData<TransRecordVo> page);

    void saveTransRecord(TransRecordDto transRecordDto);
}
