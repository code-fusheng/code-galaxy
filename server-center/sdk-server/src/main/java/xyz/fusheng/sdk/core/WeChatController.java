package xyz.fusheng.sdk.core;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @FileName: WeChatController
 * @Author: code-fusheng
 * @Date: 2021/12/31 11:09 下午
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    /**
     * 微信公众号接口接入配置调试
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping(value = "oaCallBack")
    public Object oaCallBack(@RequestParam("signature") String signature,
                                       @RequestParam("timestamp") String timestamp,
                                       @RequestParam("nonce") String nonce,
                                       @RequestParam("echostr") String echostr) {
        log.info("[微信公众号接入接口-参数预览] -> signature:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        // TODO 签名校验
        return echostr;
    }

    @PostMapping(value = "oaCallBack")
    public void oaCallBack(@RequestBody JSONObject jsonObject) {
        log.info("[{}]", jsonObject);
    }

}
