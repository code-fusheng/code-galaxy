package xyz.fusheng.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.model.entity.UserExamRecord;

@Mapper
public interface UserExamRecordMapper extends BaseMapper<UserExamRecord> {
}
