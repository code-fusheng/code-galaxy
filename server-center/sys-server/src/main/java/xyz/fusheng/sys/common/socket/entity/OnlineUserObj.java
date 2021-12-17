package xyz.fusheng.sys.common.socket.entity;

import lombok.Data;

/**
 * @FileName: OnlineUserObj
 * @Author: code-fusheng
 * @Date: 2021/12/15 11:17 上午
 * @Version: 1.0
 * @Description: 在线用户
 */

@Data
public class OnlineUserObj {

    private String userId;

    private String username;

    private String orgId;

    private String orgName;

    private Integer role;

}
