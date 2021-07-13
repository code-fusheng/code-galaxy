package xyz.fusheng.exam.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.dto.OptionDto;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.exam.core.service.OptionService;

import javax.annotation.Resource;

/**
 * @FileName: OptionController
 * @Author: code-fusheng
 * @Date: 2021/4/25 9:41 上午
 * @Version: 1.0
 * @Description: 选项接口控制器
 */

@RestController
@RequestMapping("/admin/option")
@Api(tags = "选项管理", value = "选项接口管理")
public class OptionController {

    @Resource
    private OptionService optionService;

    @PostMapping("saveOption")
    public ResultVo<Object> saveOption(@RequestBody @Validated OptionDto optionDto) {
        optionService.saveOption(optionDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

}
