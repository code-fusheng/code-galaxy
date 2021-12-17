package xyz.fusheng.sys.common.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.sys.common.socket.entity.OnlineUserObj;
import xyz.fusheng.sys.common.socket.entity.RoomObj;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @FileName: WebSocketController
 * @Author: code-fusheng
 * @Date: 2021/12/15 9:05 上午
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    private static final int TTL_MIN_30 = 1800;

    private static final String ROOM_KEY_PREFIX = "editRoom:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/test")
    public ResultVo<Object> test(@RequestBody Object object) {
        redisTemplate.opsForValue().set("xxx", object);
        return new ResultVo<>(object);
    }

    @PostMapping("/sync")
    public ResultVo<Object> sync(@RequestBody RoomObj object) throws IOException {
        log.info("[前端sync同步消息:{}]", object);
        Object value = redisTemplate.opsForValue().get(ROOM_KEY_PREFIX + object.getCaseNo());
        log.info("[Redis历史缓存信息:{}]", value);
        RoomObj oldRoomObj = JSONObject.parseObject(String.valueOf(value), RoomObj.class);
        oldRoomObj.setEditObj(object.getEditObj());
        redisTemplate.opsForValue().set(ROOM_KEY_PREFIX + object.getCaseNo(), JSON.toJSON(oldRoomObj));
        WebSocketServer.sendInfo(JSON.toJSONString(object));
        return new ResultVo<>(object);
    }

}
