package xyz.fusheng.user.common.rabbitmq;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import xyz.fusheng.core.feign.SysFeignClientServer;
import xyz.fusheng.core.model.entity.User;
import xyz.fusheng.core.model.sys.entity.LoginLog;
import xyz.fusheng.core.utils.AddressUtils;
import xyz.fusheng.user.core.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @FileName: CommonMQListener
 * @Author: code-fusheng
 * @Date: 2021/1/27 3:48 下午
 * @Version: 1.0
 * @Description:
 */

@Component
public class CommonMqListener {

    private static final Logger logger = LoggerFactory.getLogger(CommonMqListener.class);

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SysFeignClientServer sysFeignClientServer;

    @RabbitListener(queues = "${spring.profiles.active}.log.login-address.queue", containerFactory = "singleListenerContainer")
    public void consumeLogLoginQueue(@Payload byte[] message) {
        try {
            LoginLog loginLog = objectMapper.readValue(message, LoginLog.class);
            logger.info("消费者监听到用户登陆日志消息: -> {}", loginLog);
            sysFeignClientServer.saveLoginLog(loginLog);
            //更新用户地理信息位置
            User user = userMapper.selectById(loginLog.getUserId());
            Map<String, Object> addressMap = AddressUtils.getIpAddressInfo(loginLog.getIpAddress());
            userMapper.update(user, new UpdateWrapper<User>().lambda()
                    .eq(User::getUserId, user.getUserId())
                    .set(User::getAddress, loginLog.getLoginLocation())
                    .set(User::getLng, addressMap.containsKey("lng")?addressMap.get("lng").toString():null)
                    .set(User::getLat, addressMap.containsKey("lat")?addressMap.get("lat").toString():null));
            logger.info("消费者处理用户登陆日志进行更新用户地理位置信息操作: -> {}", addressMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
