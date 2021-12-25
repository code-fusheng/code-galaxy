package xyz.fusheng.bill.model.dto.socket;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName: MessageDto
 * @Author: code-fusheng
 * @Date: 2021/12/23 12:57 下午
 * @Version: 1.0
 * @Description:
 */

@Data
@NoArgsConstructor
public class MessageDto {

    private String eventType;

    private String roomId;

    private String roomType;

    private Object messageBody;

}
