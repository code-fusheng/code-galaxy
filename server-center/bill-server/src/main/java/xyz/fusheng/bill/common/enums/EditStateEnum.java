package xyz.fusheng.bill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @FileName: EditStateEnum
 * @Author: code-fusheng
 * @Date: 2021/12/23 11:50 下午
 * @Version: 1.0
 * @Description:
 */

@Getter
@AllArgsConstructor
public enum EditStateEnum {

    LOCKING("LOCKING", "锁定"),
    NO_LOCKED("NO_LOCKED", "未锁定"),
    ;

    private String code;

    private String desc;

}
