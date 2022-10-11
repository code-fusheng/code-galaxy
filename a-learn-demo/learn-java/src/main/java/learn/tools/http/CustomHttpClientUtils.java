package learn.tools.http;

import com.google.common.base.Charsets;
import org.apache.http.entity.ContentType;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @FileName: HttpUtilsAbstract
 * @Author: code-fusheng
 * @Date: 2022/3/14 10:43 上午
 * @Version: 1.0
 * @Description:
 */

public class CustomHttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(CustomHttpClientUtils.class);

    private static final int maxConcurrentConnections = 3;
    private static final int connectTimeout = 60 * 1000;
    private static final int readTimeout = 60 * 1000;
    private static final boolean automaticRetry = false;
    private static final Charset charset = Charsets.UTF_8;

    private static URLConnection getUrlConnection(String url) throws IOException {
        URL realUrl = new URL(url);
        URLConnection connection = realUrl.openConnection();
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.setRequestProperty("Charset", charset.toString());
        connection.setRequestProperty("Content-type", ContentType.APPLICATION_JSON.getMimeType());
        connection.setReadTimeout(readTimeout);
        connection.setConnectTimeout(connectTimeout);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        return connection;
    }

    public static String post(String url, Object body) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String finalUrl = url;
            logger.info("[Http工具] -> finalUrl:{}", finalUrl);
            URLConnection connection = getUrlConnection(finalUrl);
            OutputStream outputStream = connection.getOutputStream();
            if (body != null && !TextUtils.isEmpty(String.valueOf(body))) {
                byte[] writeBytes = body.toString().getBytes(StandardCharsets.UTF_8);
                connection.setRequestProperty("Content-Length", String.valueOf(writeBytes.length));
                out = new PrintWriter(outputStream);
                out.print(body);
                out.flush();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
                logger.info("[Http工具] -> result:{}", result);
            }
        } catch (IOException e) {
            logger.error("[Http工具-调用post方法-异常] -> 异常信息:{}", e.getMessage(), e);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String post = post("http://localhost:8080/test/testPost", new Object());
    }

}
