package xyz.fusheng.core.model.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @FileName: BaseDto
 * @Author: code-fusheng
 * @Date: 2021/4/12 4:21 下午
 * @Version: 1.0
 * @Description: 基础数据传输对象
 */

@Data
public class BaseDto implements Serializable {

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拓展")
    private String memo;

    @ApiModelProperty(value = "乐观锁 默认1")
    private Integer version;

    @ApiModelProperty(value = "是否启用(1:已启用/0:未启用)")
    private Integer isEnabled;

    @ApiModelProperty(value = "是否逻辑删除(1:已删除/0:未删除)")
    @TableLogic(delval = "1", value = "0")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建者编号")
    private Long creatorId;

    @ApiModelProperty(value = "修改者编号")
    private Long updaterId;

    @ApiModelProperty(value = "创建者姓名")
    private String creatorName;

    @ApiModelProperty(value = "修改者姓名")
    private String updaterName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updatedTime;

}
