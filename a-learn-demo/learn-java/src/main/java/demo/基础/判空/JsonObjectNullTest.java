package demo.基础.判空;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

/**
 * @FileName: JSONObjectNullTest
 * @Author: code-fusheng
 * @Date: 2021/8/20 4:33 下午
 * @Version: 1.0
 * @Description:
 */

public class JsonObjectNullTest {

    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));

                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args) {

        String str = "{\n" +
                "    \"attachFiles\":[\n" +
                "        {}\n" +
                "    ],\n" +
                "    \"contractNo\":\"HT-10010545759\",\n" +
                "    \"idempotentNo\":[\n" +
                "        \"RT2108205381749414\"\n" +
                "    ],\n" +
                "    \"operatorAt\":1629707941045,\n" +
                "    \"operatorId\":100000000000009902,\n" +
                "    \"operatorName\":\"曹杰\",\n" +
                "    \"orderNo\":[\n" +
                "        \"10010545760\"\n" +
                "    ]\n" +
                "}";
        System.out.println(JSON.parse(str));
        ContractUpload contractUpload = JSONObject.parseObject(str, ContractUpload.class);
        System.out.println(contractUpload);
        System.out.println(CollectionUtils.isEmpty(contractUpload.getAttachFiles()));
        System.out.println(contractUpload.getAttachFiles().get(0));
        System.out.println(checkObjAllFieldsIsNull(contractUpload.getAttachFiles().get(0)));

    }

}



@Data
class ContractUpload {

    private ArrayList<String> orderNo = new ArrayList<>(1);

    private ArrayList<String> idempotentNo = new ArrayList<>(1);

    private String contractNo;

    private ArrayList<AttachFile> attachFiles = new ArrayList<AttachFile>(2);

    private String operatorId;

    private String operatorName;

    private Date operatorAt;

}

@Data
class AttachFile {

    private String fileName;

    private String fileUrl;

    private Integer fileBusinessType;

}
