package xyz.fusheng.admin.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.admin.user.core.service.MenuService;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.vo.MenuVo;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.tool.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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

    @ApiOperation("获取可用权限列表")
    @GetMapping("/getMenuList")
    public ResultVo<List<Menu>> getMenuList() {
        List<Menu> menuList = menuService.getMenuList();
        return new ResultVo<>("操作成功: 获取可用权限列表!", menuList);
    }

    @ApiOperation("角色的权限Ids")
    @GetMapping("/getMenuIdsByRoleId/{roleId}")
    public ResultVo<List<Long>> getMenuIdsByRoleId(@PathVariable Long roleId) {
        List<Long> menuIds = menuService.getMenuIdsByRoleId(roleId);
        return new ResultVo<>("操作成功: 角色的权限Ids", menuIds);
    }

    @ApiOperation("分页查询权限列表")
    @PostMapping("/getMenuByPage")
    public ResultVo<Page<MenuVo>> getMenuByPage(@RequestBody Page<MenuVo> page) {
        String newSortColumn = StringUtils.upperCharToUnderLine(page.getSortColumn());
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 权限名称，权限级别，权限父级id 创建时间， 更新时间
            String[] sortColumns = {"name", "menu_id", "level", "pid", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = menuService.getMenuByPage(page);
        return new ResultVo<>("操作提示: 分页查询成功!", page);
    }

    /**
     * Feign
     * @param path
     * @return
     */
    @GetMapping("/getMenuByPath")
    @ApiOperation(value = "通过路径获取权限角色要求", notes = "通过路径获取权限角色要求")
    public Menu getMenuByPath(@RequestParam String path) {
        return menuService.getMenuByPath(path);
    }


}
