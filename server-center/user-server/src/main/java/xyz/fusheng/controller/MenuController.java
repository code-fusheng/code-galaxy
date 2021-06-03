package xyz.fusheng.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.model.entity.Menu;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.service.MenuService;

import javax.annotation.Resource;

/**
 * @FileName: MenuController
 * @Author: code-fusheng
 * @Date: 2021/4/14 10:40 上午
 * @Version: 1.0
 * @Description: 权限控制器
 */

@RestController
@RequestMapping("/menu")
@Api(tags = "权限接口", value = "权限接口")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/api/getMenuByPath/{path}")
    @ApiOperation(value = "通过路径获取权限角色要求", notes = "通过路径获取权限角色要求")
    public ResultVo<Menu> getMenuByPath(@PathVariable String path) {
        Menu menu = menuService.getMenuByPath(path);
        return new ResultVo<>("操作提示:获取权限角色成功!", menu);
    }


}
