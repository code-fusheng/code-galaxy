package xyz.fusheng.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @FileName: BusinessTypeEnum
 * @Author: code-fusheng
 * @Date: 2021/9/15 5:24 下午
 * @Version: 1.0
 * @Description: 操作类型枚举
 */

@Getter
@AllArgsConstructor
public enum BusinessTypeEnum implements IEnumCode<Integer> {

    OTHER(0, "其它"),
    INSERT(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    SELECT(4, "查询"),
    GRANT(5, "授权"),
    EXPORT(6, "导出"),
    IMPORT(7, "导入"),
    FORCE(8, "强退"),
    ENABLE(10, "启用"),
    DISABLE(11, "弃用"),
    READ(12, "阅读"),
    REGISTER(13, "注册")
    ;

    @EnumValue
    private Integer code;
    private String desc;

    @JsonValue
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @JsonCreator
    public static BusinessTypeEnum of(Integer code) { return IEnumCode.of(BusinessTypeEnum.class, code); }
}
