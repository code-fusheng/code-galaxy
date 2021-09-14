package xyz.fusheng.core.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

public class DictUtil {

    /**
     * 将枚举项转化为字典项
     *
     * @param enumClass 枚举类class
     * @param fieldList 枚举类中的字段
     * @return 字典项数组
     * @throws Exception
     */
    public static JSONArray resolveEnum(Class enumClass, List<Field> fieldList) throws Exception {
        JSONArray jsonArray = new JSONArray();
        Object[] enumConstants = enumClass.getEnumConstants();

        for (Object enumConstant : enumConstants) {
            JSONObject dictItem = new JSONObject();

            for (Field field : fieldList) {
                field.setAccessible(true);
                dictItem.put(field.getName(), field.get(enumConstant));
            }
            jsonArray.add(dictItem);
        }
        return jsonArray;
    }
}
