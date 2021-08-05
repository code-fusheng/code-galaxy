package xyz.fusheng.sys.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.entity.LoginLog;

/**
 * @FileName: LoginLogMapper
 * @Author: code-fusheng
 * @Date: 2021/7/7 上午10:52
 * @Version: 1.0
 * @Description:
 */

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
