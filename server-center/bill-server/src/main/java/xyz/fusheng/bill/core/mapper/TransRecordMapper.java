package xyz.fusheng.bill.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.bill.model.entity.TransRecord;
import xyz.fusheng.bill.model.vo.TransRecordVo;
import xyz.fusheng.core.model.base.PageData;

import java.util.List;

@Mapper
public interface TransRecordMapper extends BaseMapper<TransRecord> {

    List<TransRecordVo> pageTransRecordList(PageData<TransRecordVo> page);

    int countTransRecordPage(PageData<TransRecordVo> page);
}
