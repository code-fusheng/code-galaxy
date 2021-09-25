package xyz.fusheng.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @FileName: SourceMarkEnum
 * @Author: code-fusheng
 * @Date: 2021/9/13 3:04 下午
 * @Version: 1.0
 * @Description: 来源标记枚举
 */

@AllArgsConstructor
@Getter
public enum SourceMarkEnum implements IEnumCode<Integer> {

    INTER(0, "其他"),
    OUTER(1, "站内"),
    OTHER(2, "站外")
    ;

    @EnumValue
    private Integer code;
    private String desc;

    @JsonCreator
    public static SourceMarkEnum of(Integer code) { return IEnumCode.of(SourceMarkEnum.class, code); }

}
