package xyz.fusheng.exam.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.fusheng.exam.core.mapper.UserExamRecordMapper;
import xyz.fusheng.exam.core.service.UserExamRecordService;
import xyz.fusheng.exam.model.dto.UserExamRecordDto;
import xyz.fusheng.exam.model.entity.UserExamRecord;

import javax.annotation.Resource;

@Service
public class UserExamRecordServiceImpl implements UserExamRecordService {

    @Resource
    private UserExamRecordMapper userExamRecordMapper;

    /**
     * 保存用户考试记录
     * @param userExamRecordDto
     */
    @Override
    public void saveUserExamRecord(UserExamRecordDto userExamRecordDto) {
        UserExamRecord userExamRecord = new UserExamRecord();
        BeanUtils.copyProperties(userExamRecordDto, userExamRecord);
        userExamRecordMapper.insert(userExamRecord);
    }
}
