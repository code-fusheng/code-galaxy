package learn.tools.dingding;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @FileName: DingdingNoticeTest
 * @Author: code-fusheng
 * @Date: 2022/3/25 10:21
 * @Version: 1.0
 * @Description:
 */

public class DingDingNoticeTest {

    private static final Logger logger = LoggerFactory.getLogger(DingDingNoticeTest.class);

    private static String dingdingSecret = "SEC060d1e56143fc0c19abad709667cede8759a888fd6d8f8f94782c0ea4b0373df";
    private static String dingdingToken = "93aea1b9f30ab0b70925006d727ecb2b424b9303e01e499d6d67c33dca0cc256";
    private static String dingdingUrl = "https://oapi.dingtalk.com/robot/send";

    private static String dingdingSecret2 = "SEC54e417e5553d2141cde91319b385ee1fadf75dcd8304d7053f367f578e73035f";
    private static String dingdingToken2 = "35a616a96a58dff683c25f72888f251e8ae09ac0d8ea169ef9f52977ba232793";

    private static void toDingDing(String webHookToken, String contentMsg, String dingdingSecretMessage) throws Exception {

        logger.info("请求发送钉钉地址参数:{},发送信息数据:{}", webHookToken, contentMsg);

        String textMsg="{\"msgtype\": \"text\",\"text\": {\"content\": " + JSON.toJSONString(contentMsg) + "}}";
        String dingdingSecretData = dingdingSecret;
        if(StringUtils.isNotBlank(dingdingSecretMessage)){
            dingdingSecretData = dingdingSecretMessage;
            textMsg="{\"msgtype\": \"text\",\"text\": {\"content\": " + JSON.toJSONString(contentMsg) + "},\"at\":{\"isAtAll\":\"true\"}}";
        }

        //加签
        if(dingdingSecretData != null){
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + dingdingSecretData;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(dingdingSecretData.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            webHookToken += "&timestamp=" + timestamp + "&sign=" + sign;
        }

        try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httppost = new HttpPost(webHookToken);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");

            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);

            httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送钉钉信息异常:{}", e);
        }
    }

    public static void dingdingMsg(String msg){
        try {
            toDingDing(dingdingUrl + "?access_token=" + dingdingToken, "【汉唐科技】- " + msg, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送钉钉信息异常:{}", e);
        }
    }

    public static void dingdingTipMsg(String msg){
        try {
            toDingDing(dingdingUrl + "?access_token=" + dingdingToken2, msg, dingdingSecret2);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送钉钉信息异常:{}", e);
        }
    }

    public static void main(String[] args) {

          dingdingMsg("xxxxxxxx");
        // dingdingTipMsg("测试");

    }

}
