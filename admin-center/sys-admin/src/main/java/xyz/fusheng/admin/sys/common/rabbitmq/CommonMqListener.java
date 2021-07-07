package xyz.fusheng.admin.sys.common.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import xyz.fusheng.admin.sys.core.mapper.LoginLogMapper;
import xyz.fusheng.core.model.sys.entity.LoginLog;

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
    private LoginLogMapper loginLogMapper;

    @RabbitListener(queues = "${spring.profiles.active}.log.login-address.queue", containerFactory = "singleListenerContainer")
    public void consumeLogLoginQueue(@Payload byte[] message) {
        try {
            LoginLog loginLog = objectMapper.readValue(message, LoginLog.class);
            logger.info("消费者监听到用户登陆日志消息: -> {}", loginLog);
            loginLogMapper.insert(loginLog);
            // 更新用户地理信息位置
            //            User user = userService.getById(loginLog.getUserId());
            //            Map<String, Object> addressMap = AddressUtils.getIpAddressInfo(loginLog.getIpAddress());
            //            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
            //            userUpdateWrapper.lambda().eq(User::getUserId, user.getUserId());
            //            userUpdateWrapper.lambda().set(User::getAddress, loginLog.getLoginLocation());
            //            userUpdateWrapper.lambda().set(User::getLng, addressMap.containsKey("lng")?addressMap.get("lng").toString():null);
            //            userUpdateWrapper.lambda().set(User::getLat, addressMap.containsKey("lat")?addressMap.get("lat").toString():null);
            //            userService.update(userUpdateWrapper);
            //            logger.info("消费者处理用户登陆日志进行更新用户地理位置信息操作: -> {}", addressMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
