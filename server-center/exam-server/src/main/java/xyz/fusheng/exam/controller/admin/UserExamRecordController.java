package xyz.fusheng.exam.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.dto.UserExamRecordDto;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.exam.core.service.UserExamRecordService;

import javax.annotation.Resource;

/**
 * @FileName: UserExamRecordController
 * @Author: code-fusheng
 * @Date: 2021/5/11 2:25 下午
 * @Version: 1.0
 * @Description: 用户考试记录控制类
 */

@RestController
@RequestMapping("/userExamRecord")
@Api(tags = "成绩管理", value = "成绩接口管理")
public class UserExamRecordController {

    @Resource
    private UserExamRecordService userExamRecordService;

    @ApiOperation(value = "保存用户考试记录")
    @PostMapping("/saveUserExamRecord")
    public ResultVo<Object> saveUserExamRecord(@RequestBody @Validated UserExamRecordDto userExamRecordDto) {
        userExamRecordService.saveUserExamRecord(userExamRecordDto);
        return new ResultVo<>("操作提示: 保存用户考试记录成功!");
    }

}
