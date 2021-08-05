package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.model.dto.UserExamRecordDto;

public interface UserExamRecordService{

    /**
     * 保存用户考试记录
     * @param userExamRecordDto
     */
    void saveUserExamRecord(UserExamRecordDto userExamRecordDto);

}
