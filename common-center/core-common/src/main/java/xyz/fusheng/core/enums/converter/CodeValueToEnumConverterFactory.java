package xyz.fusheng.core.enums.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import xyz.fusheng.core.enums.IEnumCode;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

@Slf4j
public class CodeValueToEnumConverterFactory implements ConverterFactory<String, IEnumCode> {

    @Override
    public <T extends IEnumCode> Converter<String, T> getConverter(Class<T> targetType) {
        return new CodeValueToEnum(targetType);
    }

    private static class CodeValueToEnum<T extends IEnumCode> implements Converter<String, T> {

        private Class enumType;

        private T[] enumItems;

        private Class codeType;

        public CodeValueToEnum(Class<T> targetType) {
            enumType = targetType;
            enumItems = targetType.getEnumConstants();
            Type[] genericInterfaces = targetType.getGenericInterfaces();
            for (Type genericInterface : genericInterfaces) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                Class rawType = (Class) parameterizedType.getRawType();
                if (rawType.isAssignableFrom(IEnumCode.class)) {
                    codeType = (Class) parameterizedType.getActualTypeArguments()[0];
                }
            }
        }

        @Override
        public T convert(String source) {
            Object sourceValue = null;
            try {
                if (codeType.isAssignableFrom(Integer.class)) {
                    sourceValue = Integer.parseInt(source);
                } else {
                    sourceValue = source;
                }
            } catch (Exception e) {
                log.error("枚举转换失败。枚举项：{} code={}值转换异常", enumType, source, e.getMessage(), e);
            }

            for (T t : enumItems) {
                if (sourceValue != null && Objects.equals(t.getCode(), sourceValue)) {
                    return t;
                }
            }

            return null;
        }
    }
}
