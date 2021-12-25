package xyz.fusheng.bill.model.dto.socket;

import lombok.Data;

/**
 * @FileName: OnlineUserDto
 * @Author: code-fusheng
 * @Date: 2021/12/22 1:34 下午
 * @Version: 1.0
 * @Description: 在线用户Dto
 */

@Data
public class OnlineUserDto {

    private String cid;

    private String userId;

    private String username;

    private String userRole;

}
