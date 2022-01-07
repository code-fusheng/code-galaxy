package xyz.fusheng.bill.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @FileName: RedisChannelListenner
 * @Author: code-fusheng
 * @Date: 2021/12/26 4:37 下午
 * @Version: 1.0
 * @Description:
 */

@Slf4j
public class RedisChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("接受到跨客户端消息:{}", message);
    }
}
