package xyz.fusheng.bill.core.service;

import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.bill.dto.TransRecordDto;
import xyz.fusheng.core.model.bill.vo.TransRecordVo;

public interface TransRecordService{

    Page<TransRecordVo> pageTransRecord(Page<TransRecordVo> page);

    void saveTransRecord(TransRecordDto transRecordDto);
}
