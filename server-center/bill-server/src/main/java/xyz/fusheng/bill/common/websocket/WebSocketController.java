package xyz.fusheng.bill.common.websocket;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.model.vo.ResultVo;

/**
 * @FileName: WebSocketController
 * @Author: code-fusheng
 * @Date: 2021/12/14 10:07 下午
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    public ResultVo<Object> editContent(@RequestBody Object object) {
        try {

        } catch (Exception e) {

        }
        return new ResultVo<>("1");
    }

}
