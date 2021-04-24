package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.exam.service.QuestionService;
import xyz.fusheng.model.dto.QuestionDto;
import xyz.fusheng.model.vo.ResultVo;

import javax.annotation.Resource;

/**
 * @FileName: QuestionController
 * @Author: code-fusheng
 * @Date: 2021/4/24 11:10 下午
 * @Version: 1.0
 * @Description: 试题控制类
 */

@RestController
@RequestMapping("/question")
@Api(tags = "试题管理", value = "试题管理接口")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    public ResultVo<Object> saveQuestion(@RequestBody @Validated QuestionDto questionDto) {
        questionService.saveQuestion(questionDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

}
