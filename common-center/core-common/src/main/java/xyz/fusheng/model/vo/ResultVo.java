package xyz.fusheng.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.enums.ResultEnums;

import java.io.Serializable;

/**
 * @FileName: ResultVo
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:35 上午
 * @Version: 1.0
 * @Description: 统一返回结果
 */

@ApiModel(value = "统一返回结果-视图对象")
@Data
public class ResultVo<T> implements Serializable {

    /**
     * 1. File -> setting -> plugins  安装 GenerateSerialVersionUID 插件
     * 2. Win 光标 Serializable Alt+Insert 生成序列化唯一标识
     * 3. Mac Serializable command + N
     */

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "消息提示")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 默认情况下返回成功结果集
     * 说明 ： 以下情况如何理解
     * 只有msg参数，说明请求未返回结果，比如保存，修改等操作，直接对应信息
     * 害。具体情况集体分析吧
     */
    public ResultVo() {
        this.code = ResultEnums.SUCCESS.getCode();
        this.msg = ResultEnums.SUCCESS.getMsg();
    }

    /**
     * 默认失败
     * @param code
     * @param msg
     * @param data
     */
    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(String msg) {
        this.code = ResultEnums.SUCCESS.getCode();
        this.msg = msg;
    }
    public ResultVo(T data) {
        this.code = ResultEnums.SUCCESS.getCode();
        this.msg = ResultEnums.SUCCESS.getMsg();
        this.data = data;
    }
    public ResultVo(String msg, T data) {
        this.code = ResultEnums.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }
    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultVo(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
    }

}
