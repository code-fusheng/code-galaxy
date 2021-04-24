package xyz.fusheng.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.fusheng.model.vo.ResultVo;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: ResultUtils
 * @Author: code-fusheng
 * @Date: 2021/4/7 11:48 上午
 * @Version: 1.0
 * @Description: 统一返回结果工具类
 *
 */

public class ResultUtils {

    private static final Logger logger = LoggerFactory.getLogger(ResultUtils.class);

    /**
     * 私有化构造器
     */
    private ResultUtils(){}

    /**
     * 返回成功示例
     * @Param  resultMap  返回数据MAP
     * @Return Map<String,Object> 返回数据MAP
     */
    public static Map<String, Object> success(Map<String, Object> resultMap){
        resultMap.put("message","操作成功");
        resultMap.put("code", 200);
        return resultMap;
    }
    /**
     * 返回失败示例
     * @Param  resultMap  返回数据MAP
     * @Return Map<String,Object> 返回数据MAP
     */
    public static Map<String, Object> error(Map<String, Object> resultMap){
        resultMap.put("message","操作失败");
        resultMap.put("code",500);
        return resultMap;
    }
    /**
     * 通用示例
     * @Param  code 信息码
     * @Param  msg  信息
     * @Return Map<String,Object> 返回数据MAP
     */
    public static Map<String, Object> result(Integer code,String msg){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message",msg);
        resultMap.put("code",code);
        return resultMap;
    }


}
