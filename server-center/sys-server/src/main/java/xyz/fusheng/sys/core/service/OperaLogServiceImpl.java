package xyz.fusheng.sys.core.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.sys.core.mapper.OperaLogMapper;
import xyz.fusheng.sys.core.service.impl.OperaLogService;
@Service
public class OperaLogServiceImpl implements OperaLogService {

    @Resource
    private OperaLogMapper operaLogMapper;

}
