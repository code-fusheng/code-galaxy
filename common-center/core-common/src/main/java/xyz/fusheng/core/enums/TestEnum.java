package xyz.fusheng.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @FileName: TestEnum
 * @Author: code-fusheng
 * @Date: 2021/9/13 10:49 上午
 * @Version: 1.0
 * @Description:
 */

@AllArgsConstructor
@Getter
public enum TestEnum implements IEnumCode<Integer> {

    TEST_ONE(1, "测试")
    ;

    @EnumValue
    private Integer code;
    private String desc;

    @JsonValue
    @Override
    public Integer getCode() { return code; }

    public String getDesc() { return desc; }

    @JsonCreator
    public static TestEnum of(Integer code) { return IEnumCode.of(TestEnum.class, code); }
}
