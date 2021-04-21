package xyz.fusheng.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.base.service.DictDataService;
import xyz.fusheng.enums.ResultEnums;
import xyz.fusheng.model.base.Page;
import xyz.fusheng.model.dto.DictDataDto;
import xyz.fusheng.model.entity.DictData;
import xyz.fusheng.model.vo.DictDataVo;
import xyz.fusheng.model.vo.DictTypeVo;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: DictDataController
 * @Author: code-fusheng
 * @Date: 2021/4/16 8:24 下午
 * @Version: 1.0
 * @Description: 字典数据控制类
 */

@RestController
@RequestMapping("/dict/data")
public class DictDataController {

    @Resource
    private DictDataService dictDataService;

    @ApiOperation(value = "添加字典数据")
    @PostMapping("/saveDictData")
    public ResultVo<Object> saveDictData(@RequestBody @Validated DictDataDto dictDataDto) {
        dictDataService.saveDictData(dictDataDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "批量删除字典数据")
    @DeleteMapping("/deleteDictDataByCodes")
    public ResultVo<Object> deleteDictDataByCodes(@RequestBody Long[] dictCodes) {
        dictDataService.deleteDictDataByCodes(dictCodes);
        return new ResultVo<>("操作提示: 删除成功!");
    }

    @ApiOperation(value = "修改字典数据")
    @PutMapping("/updateDictData")
    public ResultVo<Object> updateDictData(@RequestBody DictDataDto dictDataDto) {
        dictDataService.updateDictData(dictDataDto);
        return new ResultVo<>("操作提示: 修改成功!");
    }

    @ApiOperation(value = "根据Code获取字典数据详情")
    @GetMapping("/getDictDataByCode/{dictDataCode}")
    public ResultVo<DictDataVo> getDictDataByCode(@PathVariable @ApiParam(value = "字典数据code编码") Long dictDataCode) {
        DictDataVo dictDataVo = dictDataService.getDictDataByCode(dictDataCode);
        return new ResultVo<>("操作提示: 获取成功!", dictDataVo);
    }

    @ApiOperation(value = "根据字典类型获取所有字典数据")
    @GetMapping("/listDictDataByDictType/{dictType}")
    public ResultVo<List<DictDataVo>> listDictDataByDictType(@PathVariable @ApiParam(value = "字典类型") String dictType) {
        List<DictDataVo> dictDataList = dictDataService.listDictDataByDictType(dictType);
        return new ResultVo<>("操作提示: 获取成功!", dictDataList);
    }


    @ApiOperation(value = "分页查询字典数据列表")
    @PostMapping("/getDictDataByPage")
    public ResultVo<Page<DictDataVo>> getDictTypeByPage(@RequestBody Page<DictDataVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 字典排序、字典标签、创建时间、更新时间
            String[] sortColumns = {"dict_sort", "dict_label", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = dictDataService.getDictDataByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
