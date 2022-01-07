package xyz.fusheng.bill.model.dto.socket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: ColumnDto
 * @Author: code-fusheng
 * @Date: 2021/12/22 10:51 上午
 * @Version: 1.0
 * @Description: 字段Dto
 */

@Data
@NoArgsConstructor
public class ColumnDto {

    private String columnKey;

    private Object columnValue;

    private String editState;

    private List<String> currentUser;

    public ColumnDto(String columnKey) {
        this.columnKey = columnKey;
        this.columnValue = "";
        this.editState = "NO_LOCKING";
        this.currentUser = new ArrayList<>();
    }
}
