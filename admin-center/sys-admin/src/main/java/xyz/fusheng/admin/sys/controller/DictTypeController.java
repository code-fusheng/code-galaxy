package xyz.fusheng.admin.sys.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.admin.sys.common.annotation.UserInfo;
import xyz.fusheng.admin.sys.core.service.DictTypeService;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.dto.DictTypeDto;
import xyz.fusheng.core.model.entity.DictType;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.DictTypeVo;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: DictTypeController
 * @Author: code-fusheng
 * @Date: 2021/4/12 10:27 下午
 * @Version: 1.0
 * @Description: 字典类型控制类
 */

@RestController
@RequestMapping("/dict/type")
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    @ApiOperation(value = "添加字典类型", notes = "添加字典类型")
    @PostMapping("/saveDictType")
    public ResultVo<Object> saveDictType(@RequestBody @Validated DictTypeDto dictTypeDto,
                                         @ApiIgnore @UserInfo SelfUser userInfo) {
        dictTypeDto.setCreatorId(userInfo.getUserId());
        dictTypeDto.setCreatorName(userInfo.getUsername());
        dictTypeService.saveDictType(dictTypeDto);
        return new ResultVo<>("操作提示: 添加成功!");
    }

    @ApiOperation(value = "批量删除字典类型")
    @DeleteMapping("/deleteDictTypeByIds")
    public ResultVo<Object> deleteDictTypeByIds(@RequestBody Long[] dictIds) {
        dictTypeService.deleteDictTypedByIds(dictIds);
        return new ResultVo<>("操作提示: 删除成功!");
    }

    @ApiOperation(value = "修改字典类型")
    @PutMapping("/updateDictType")
    public ResultVo<Object> updateDictType(@RequestBody DictTypeDto dictTypeDto,
                                           @ApiIgnore @UserInfo SelfUser userInfo) {
        dictTypeDto.setUpdaterId(userInfo.getUserId());
        dictTypeDto.setUpdaterName(userInfo.getUsername());
        dictTypeService.updateDictType(dictTypeDto);
        return new ResultVo<>("操作提示: 修改成功!");
    }

    @ApiOperation(value = "根据Id获取字典类型详情")
    @GetMapping("/getDictTypeById/{dictTypeId}")
    public ResultVo<DictType> getDictTypeById(@PathVariable @ApiParam(value = "字典类型Id", example = "1384749237748654082") Long dictTypeId) {
        DictType dictType = dictTypeService.getDictTypeById(dictTypeId);
        return new ResultVo<>("操作提示: 获取成功!", dictType);
    }

    @ApiOperation(value = "分页查询字典类型列表")
    @PostMapping("/getDictTypeByPage")
    public ResultVo<Page<DictTypeVo>> getDictTypeByPage(@RequestBody Page<DictTypeVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 字典名称、字典类型、创建时间、更新时间
            String[] sortColumns = {"dict_name", "dict_type", "created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = dictTypeService.getDictTypeByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

}
