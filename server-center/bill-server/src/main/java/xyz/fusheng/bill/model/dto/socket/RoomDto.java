package xyz.fusheng.bill.model.dto.socket;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @FileName: RoomDto
 * @Author: code-fusheng
 * @Date: 2021/12/22 10:38 上午
 * @Version: 1.0
 * @Description:
 */

@Data
@ApiModel("房间信息传输对象")
public class RoomDto {

    private String roomId;

    private String roomType;

    // 房主
    private String userId;

}
