package xyz.fusheng.core.enums;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @FileName: IEnumCode
 * @Author: code-fusheng
 * @Date: 2021/9/13 11:18 上午
 * @Version: 1.0
 * @Description:
 */

public interface IEnumCode<RetType> {

    RetType getCode();

    static <T extends IEnumCode> T of(Class<T> clazz, Object code) {
        return Stream.of(clazz.getEnumConstants())
                .filter(constant -> Objects.equals(constant.getCode(), code))
                .findFirst().orElse(null);
    }

}
