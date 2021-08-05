package xyz.fusheng.sys.controller.admin;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.SecurityUtils;
import xyz.fusheng.sys.core.service.DictTypeService;
import xyz.fusheng.sys.model.dto.DictTypeDto;
import xyz.fusheng.sys.model.entity.DictType;
import xyz.fusheng.sys.model.vo.DictTypeVo;

import javax.annotation.Resource;

/**
 * @FileName: DictTypeController
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:27 下午
 * @Version: 1.0
 * @Description: 字典类型控制类
 */

@RestController
@RequestMapping("/admin/dict/type")
public class AdminDictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    @ApiOperation(value = "添加字典类型", notes = "添加字典类型")
    @PostMapping("/saveDictType")
    public ResultVo<Object> saveDictType(@RequestBody @Validated DictTypeDto dictTypeDto) {
        SelfUser userInfo = SecurityUtils.getUserInfo();
        dictTypeDto.setCreatorId(userInfo.getUserId());
        dictTypeDto.setCreatorName(userInfo.getUsername());
        dictTypeService.saveDictType(dictTypeDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "批量删除字典类型")
    @DeleteMapping("/deleteDictType")
    public ResultVo<Object> deleteDictType(@RequestBody Long[] dictIds) {
        dictTypeService.deleteDictType(dictIds);
        return new ResultVo<>("操作提示: 删除成功!");
    }

    @ApiOperation(value = "修改字典类型")
    @PutMapping("/updateDictType")
    public ResultVo<Object> updateDictType(@RequestBody DictTypeDto dictTypeDto) {
        SelfUser userInfo = SecurityUtils.getUserInfo();
        dictTypeDto.setUpdaterId(userInfo.getUserId());
        dictTypeDto.setUpdaterName(userInfo.getUsername());
        dictTypeService.updateDictType(dictTypeDto);
        return new ResultVo<>("操作提示: 修改成功!");
    }

    @ApiOperation(value = "根据Id获取字典类型详情")
    @GetMapping("/infoDictType/{dictTypeId}")
    public ResultVo<DictType> infoDictType(@PathVariable @ApiParam(value = "字典类型Id", example = "1384749237748654082") Long dictTypeId) {
        DictType dictType = dictTypeService.infoDictType(dictTypeId);
        return new ResultVo<>("操作提示: 获取成功!", dictType);
    }

    @ApiOperation(value = "分页查询字典类型列表")
    @PostMapping("/pageDictType")
    public ResultVo<PageData<DictTypeVo>> pageDictType(@RequestBody PageData<DictTypeVo> page) {
        page = dictTypeService.pageDictType(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
