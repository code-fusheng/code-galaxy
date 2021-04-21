package xyz.fusheng.model.vo;

import lombok.Data;
import xyz.fusheng.model.entity.DictType;

/**
 * @FileName: DictTypeVo
 * @Author: code-fusheng
 * @Date: 2021/4/20 11:15 上午
 * @Version: 1.0
 * @Description: 字典类型视图对象
 */

@Data
public class DictTypeVo extends DictType {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

}
