package xyz.fusheng.admin.exam.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.admin.exam.core.service.SearchService;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.annotation.Resource;

/**
 * @FileName: SearchController
 * @Author: code-fusheng
 * @Date: 2021/6/15 3:52 下午
 * @Version: 1.0
 * @Description: 搜索控制类
 */

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @ApiOperation(value = "创建简单Index索引")
    @PutMapping("/newSimpleIndex/{index}")
    public ResultVo<Object> newSimpleIndex(@PathVariable String index) {
        searchService.newSimpleIndex(index);
        return new ResultVo<>("操作成功!");
    }

    @PutMapping("/bulkSync")
    public ResultVo<Object> bulkSync() {
        searchService.bulkSync();
        return new ResultVo<>("操作成功!");
    }

}
