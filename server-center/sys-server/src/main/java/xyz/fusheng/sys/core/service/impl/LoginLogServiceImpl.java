package xyz.fusheng.sys.core.service.impl;

import org.springframework.stereotype.Service;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.LoginLog;
import xyz.fusheng.core.utils.StringUtils;
import xyz.fusheng.sys.core.mapper.LoginLogMapper;
import xyz.fusheng.sys.core.service.LoginLogService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: LoginLogServiceImpl
 * @Author: code-fusheng
 * @Date: 2021/7/7 上午10:52
 * @Version: 1.0
 * @Description:
 */

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageData<LoginLog> pageLoginLog(PageData<LoginLog> page) {
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 创建时间、更新时间
            String[] sortColumns = {"created_time", "updated_time", "login_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(page.getSortColumn().toLowerCase())) {
                throw new BusinessException(ResultEnums.ERROR.getCode(), "参数错误!");
            }
        }
        // 查询数据
        List<LoginLog> loginLogList = loginLogMapper.getByPage(page);
        page.setList(loginLogList);
        // 统计总数
        int totalCount = loginLogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
