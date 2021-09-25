package xyz.fusheng.sys.controller.admin;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.core.annotation.UserInfo;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.sys.core.service.DictDataService;
import xyz.fusheng.sys.model.dto.DictDataDto;
import xyz.fusheng.sys.model.vo.DictDataVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: DictDataController
 * @Author: code-fusheng
 * @Date: 2021/4/16 8:24 下午
 * @Version: 1.0
 * @Description: 字典数据控制类
 */

@RestController
@RequestMapping("/admin/dict/data")
public class AdminDictDataController {

    @Resource
    private DictDataService dictDataService;

    @ApiOperation(value = "添加字典数据")
    @PostMapping("/saveDictData")
    public ResultVo<Object> saveDictData(@RequestBody @Validated DictDataDto dictDataDto,
                                         @ApiIgnore @UserInfo SelfUser userInfo) {
        dictDataDto.setCreatorId(userInfo.getUserId());
        dictDataDto.setCreatorName(userInfo.getUsername());
        dictDataService.saveDictData(dictDataDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "批量删除字典数据")
    @DeleteMapping("/deleteDictData")
    public ResultVo<Object> deleteDictData(@RequestBody Long[] dictCodes) {
        dictDataService.deleteDictData(dictCodes);
        return new ResultVo<>("操作提示: 删除成功!");
    }

    @ApiOperation(value = "修改字典数据")
    @PutMapping("/updateDictData")
    public ResultVo<Object> updateDictData(@RequestBody DictDataDto dictDataDto,
                                           @ApiIgnore @UserInfo SelfUser userInfo) {
        dictDataDto.setUpdaterId(userInfo.getUserId());
        dictDataDto.setUpdaterName(userInfo.getUsername());
        dictDataService.updateDictData(dictDataDto);
        return new ResultVo<>("操作提示: 修改成功!");
    }

    @ApiOperation(value = "根据Code获取字典数据详情")
    @GetMapping("/infoDictData/{dictDataCode}")
    public ResultVo<DictDataVo> infoDictData(@PathVariable @ApiParam(value = "字典数据code编码") Long dictDataCode) {
        DictDataVo dictDataVo = dictDataService.infoDictData(dictDataCode);
        return new ResultVo<>("操作提示: 获取成功!", dictDataVo);
    }

    @ApiOperation(value = "根据字典类型获取所有字典数据")
    @GetMapping("/listDictData/{dictType}")
    public ResultVo<List<DictDataVo>> listDictData(@PathVariable @ApiParam(value = "字典类型") String dictType) {
        List<DictDataVo> dictDataList = dictDataService.listDictData(dictType);
        return new ResultVo<>("操作提示: 获取成功!", dictDataList);
    }

    @ApiOperation(value = "分页查询字典数据列表")
    @PostMapping("/getDictDataByPage")
    public ResultVo<PageData<DictDataVo>> getDictTypeByPage(@RequestBody PageData<DictDataVo> page) {
        page = dictDataService.pageDictData(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
