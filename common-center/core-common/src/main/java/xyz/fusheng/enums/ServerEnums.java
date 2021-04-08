package xyz.fusheng.enums;

import lombok.Getter;

/**
 * @FileName: CodeEnums
 * @Author: code-fusheng
 * @Date: 2021/4/8 9:53 上午
 * @Version: 1.0
 * @Description: 系统枚举常量
 */

@Getter
public enum ServerEnums {

    TEST_SERVER("test-server", "测试服务"),
    USER_SERVER("user-server", "用户服务"),
    ARTICLE_SERVER("article-server", "文章服务"),
    EXAM_SERVER("exam-server" , "考试服务")
    ;

    /**
     * code 响应码
     * msg 响应信息
     */
    private String label;
    private String value;

    ServerEnums(String label, String value) {
        this.label = label;
        this.value = value;
    }

}
