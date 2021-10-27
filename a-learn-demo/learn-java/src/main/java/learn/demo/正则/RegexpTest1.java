package learn.demo.正则;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;

/**
 * @FileName: RegexpTest1
 * @Author: code-fusheng
 * @Date: 2021/10/9 5:27 下午
 * @Version: 1.0
 * @Description:
 */

public class RegexpTest1 {

    public static void main(String[] args) {

        String content = "0901 计算机外围设备、0902 时间记录装置、0903 复印机（照相、静电、热）、0907 网络通讯设备、0908 电视机、0909 电影摄影机、0910 测量器械和仪器、0912 电源材料（电线、电缆）、0921 眼镜、0922 电池";

        String reg = "、\\d";

        String[] split = content.split(reg);
        for (String s : split) {
            System.out.println(s);
        }

    }

}
