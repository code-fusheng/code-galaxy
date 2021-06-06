package xyz.fusheng.user.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.entity.Menu;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.user.core.service.MenuService;

import javax.annotation.Resource;

/**
 * @FileName: ApiMenuController
 * @Author: code-fusheng
 * @Date: 2021/6/6 11:42 上午
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/api/menu")
public class ApiMenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/getMenuByPath/{path}")
    @ApiOperation(value = "通过路径获取权限角色要求", notes = "通过路径获取权限角色要求")
    public ResultVo<Menu> getMenuByPath(@PathVariable String path) {
        Menu menu = menuService.getMenuByPath(path);
        return new ResultVo<>("操作提示:获取权限角色成功!", menu);
    }

}
