package xyz.fusheng.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonCreator;

/**
 * @FileName: CodeEnums
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:53 上午
 * @Version: 1.0
 * @Description: 系统服务枚举
 */

@AllArgsConstructor
@Getter
public enum ServerEnum implements IEnumCode<String>{

    TEST_SERVER("test-server", "测试服务"),
    USER_SERVER("user-server", "用户服务"),
    ARTICLE_SERVER("article-server", "文章服务"),
    EXAM_SERVER("exam-server" , "考试服务")
    ;

    @EnumValue
    private String code;
    private String desc;

    @Override
    @JsonValue
    public String getCode() {
        return null;
    }

    @JsonCreator
    public static ServerEnum of(String code) { return IEnumCode.of(ServerEnum.class, code); }
}
