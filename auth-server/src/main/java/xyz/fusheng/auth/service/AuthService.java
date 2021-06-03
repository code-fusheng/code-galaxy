package xyz.fusheng.auth.service;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import xyz.fusheng.model.vo.ResultVo;
import xyz.fusheng.utils.HttpUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: AuthSerivce
 * @Author: code-fusheng
 * @Date: 2021/6/3 4:27 下午
 * @Version: 1.0
 * @Description: 认证业务逻辑类
 */

@Service
public class AuthService {

    @Resource
    LoadBalancerClient loadBalancerClient;

    public ResultVo refreshToken(String header, String refreshToken) {
        // 采用客户端负载均衡,从 Nacos获取认证服务器的IP和端口
        ServiceInstance serviceInstance = loadBalancerClient.choose("auth-server");
        if (serviceInstance == null) {
            return ResultVo.error("未找到有效认证服务器!");
        }
        // 请求刷新令牌 URL
        String refreshTokenUrl = serviceInstance.getUri().toString() + "/auth/oauth/token";
        // 封装刷新令牌请求参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        return ResultVo.success();
    }

    private URLConnection getUrlConnection(String urlStr, String header) throws IOException {
        URL realUrl = new URL(urlStr);
        URLConnection conn = realUrl.openConnection();
        conn.setRequestProperty("Authorization", header);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        return conn;
    }

}
