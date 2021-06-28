package xyz.fusheng.admin.article.common.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @FileName: JsonConfig
 * @Author: code-fusheng
 * @Date: 2020/10/8 15:20
 * @version: 1.0
 * Description: JSON序列化配置, 默认采用 Jackson
 */

@Configuration
public class JsonConfig {

    /**
     * Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
     * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer cunstomizer = jacksonObjectMapperBuilder -> {
            // 本质上就是将 Long 类型主键序列化成 String 类型
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);

        };
        return cunstomizer;
    }

}
