package xyz.fusheng.core.model.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @FileName: BaseQuery
 * @Author: code-fusheng
 * @Date: 2021/4/22 11:07 上午
 * @Version: 1.0
 * @Description:
 *
 * @Accessors 集联操作
 *
 */

@Data
@Accessors(chain = true)
public class BaseQuery<T> implements Serializable {

    @ApiModelProperty(value = "排序字段", example = "created_time")
    private String sortColumn = "created_time";

    @ApiModelProperty(value = "升序asc / 倒序desc", example = "asc")
    private String sortMethod = "asc";

    @ApiModelProperty(value = "页码", required = true)
    private long pageNum;

    @ApiModelProperty(value = "每页大小", required = true)
    private long pageSize;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "是否启用")
    private Integer isEnabled = 1;

    /**
     * 分页对象
     * @return
     */
    @ApiModelProperty(hidden = true)
    public IPage<T> getPage() {
        return new Page<T>().setCurrent(this.pageNum).setSize(this.pageSize);
    }

    /**
     * 获取排序
     * @return
     */
    public String getSortColumn() {
        if (StringUtils.isNotBlank(sortColumn)) {
            return upperCharToUnderLine(sortColumn);
        }
        return "created_time";
    }

    /**
     * 驼峰转下划线
     */
    @SuppressWarnings("all")
    public static String upperCharToUnderLine(String param) {
        StringBuilder sb = new StringBuilder(param);
        int temp = 0;
        if (!param.contains("_")) {
            for (int i = 0; i < param.length(); i++) {
                if (Character.isUpperCase(param.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

}
