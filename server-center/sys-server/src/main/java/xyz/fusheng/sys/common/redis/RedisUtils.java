package xyz.fusheng.sys.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @FileName: RedisUtils
 * @Author: code-fusheng
 * @Date: 2021/12/15 9:23 上午
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis操作异常,errMsg:{}", e.getMessage(), e);
            return false;
        }
    }

}
