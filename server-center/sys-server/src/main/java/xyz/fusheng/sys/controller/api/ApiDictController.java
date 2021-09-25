package xyz.fusheng.sys.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.enums.IEnumCode;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.query.DictQuery;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.DictUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: ApiDictController
 * @Author: code-fusheng
 * @Date: 2021/9/13 10:39 上午
 * @Version: 1.0
 * @Description: 获取字典类型
 */

@Slf4j
@Api(tags = "字典枚举")
@RestController
@RequestMapping("/api/dict")
public class ApiDictController {

    @ApiOperation("字典枚举列表")
    @PostMapping("/list")
    public ResultVo<JSONArray> list(@RequestBody DictQuery dictQuery) {
        log.info("[字典枚举-列表查询开始] => dictQuery:{}", JSON.toJSON(dictQuery));
        JSONArray dictArray = null;
        // 获取枚举包的包名
        String packageName = IEnumCode.class.getPackage().getName();
        // 拼接枚举类名
        String enumClassName = packageName + "." + dictQuery.getDictName() + "Enum";
        try {
            // 反射获取枚举类 class
            Class<?> clazz = Class.forName(enumClassName);
            if (clazz.isEnum()) {
                // 反射获取前端需要的字段
                List<Field> fieldList = new ArrayList<>(dictQuery.getFields().length);
                for (String fieldName : dictQuery.getFields()) {
                    fieldList.add(clazz.getDeclaredField(fieldName));
                }
                // 调用字典解析工具类解析枚举类，生成字典项
                dictArray = DictUtil.resolveEnum(clazz, fieldList);
            }
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            log.info("未找到字典[{}]相关数据 => 异常信息:{}", dictQuery.getDictName(), e.getMessage(), e);
            throw new BusinessException(String.format("未找到字典[%s]相关数据", dictQuery.getDictName()));
        } catch (Exception e) {
            log.info("字典[{}]异常 => 异常信息:{}", dictQuery.getDictName(), e.getMessage(), e);
            throw new BusinessException(String.format("字典[%s]异常", dictQuery.getDictName()));
        }
        log.info("[字典枚举-列表查询结束] => dictArray:{}", dictArray);
        return ResultVo.success(dictArray);
    }

}
