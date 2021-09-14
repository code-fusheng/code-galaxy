package xyz.fusheng.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * @FileName: ArticleStateEnum
 * @Author: code-fusheng
 * @Date: 2021/9/13 11:43 上午
 * @Version: 1.0
 * @Description: 文章状态枚举
 */

public enum ArticleStateEnum implements IEnumCode<Integer>{

    DRAFT(0, "草稿"),
    DELAYED(1, "延时发布"),
    PUBLISHED(2, "已经发布"),
    ;

    @EnumValue
    private Integer code;
    private String desc;

    ArticleStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    @JsonValue
    public Integer getCode() {
        return code;
    }

    public String getDesc() { return desc; }

    @JsonCreator
    public static ArticleStateEnum of(Integer code) {
        return IEnumCode.of(ArticleStateEnum.class, code);
    }

}
