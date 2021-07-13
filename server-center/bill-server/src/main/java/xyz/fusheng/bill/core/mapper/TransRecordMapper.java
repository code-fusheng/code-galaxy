package xyz.fusheng.bill.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.bill.entity.TransRecord;
import xyz.fusheng.core.model.bill.vo.TransRecordVo;

import java.util.List;

@Mapper
public interface TransRecordMapper extends BaseMapper<TransRecord> {

    List<TransRecordVo> pageTransRecordList(Page<TransRecordVo> page);

    int countTransRecordPage(Page<TransRecordVo> page);
}
