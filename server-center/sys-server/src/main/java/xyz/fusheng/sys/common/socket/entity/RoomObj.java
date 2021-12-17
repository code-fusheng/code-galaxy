package xyz.fusheng.sys.common.socket.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @FileName: RoomObj
 * @Author: code-fusheng
 * @Date: 2021/12/15 11:14 上午
 * @Version: 1.0
 * @Description:
 */

@Data
public class RoomObj {

    // 房间号
    private String caseNo;

    // 编辑目标id
    private String targetId;

    // 房间用户列表
    private List<OnlineUserObj> userList;

    private EditObj editObj;

}
