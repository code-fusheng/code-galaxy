package xyz.fusheng.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @FileName: OperatorTypeEnum
 * @Author: code-fusheng
 * @Date: 2021/9/15 5:58 下午
 * @Version: 1.0
 * @Description: 操作员类型枚举
 */

@Getter
@AllArgsConstructor
public enum OperatorTypeEnum implements IEnumCode<Integer> {

    OTHER(0, "其它"),
    ADMIN(1, "后台用户"),
    WEB(2, "前台用户"),
    MOBILE(3, "手机端用户")
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
    public static OperatorTypeEnum of(Integer code) { return IEnumCode.of(OperatorTypeEnum.class, code); }

}
