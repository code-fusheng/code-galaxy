package xyz.fusheng.bill.core.service;

import xyz.fusheng.bill.model.dto.TransRecordDto;
import xyz.fusheng.bill.model.vo.TransRecordVo;
import xyz.fusheng.core.model.base.Page;

public interface TransRecordService{

    Page<TransRecordVo> pageTransRecord(Page<TransRecordVo> page);

    void saveTransRecord(TransRecordDto transRecordDto);
}
