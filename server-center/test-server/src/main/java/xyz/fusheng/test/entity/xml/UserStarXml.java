package xyz.fusheng.test.entity.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @FileName: UserStarXml
 * @Author: code-fusheng
 * @Date: 2021/12/31 10:41 上午
 * @Version: 1.0
 * @Description:
 */

@JacksonXmlRootElement(localName = "xml")
public class UserStarXml {

    @JacksonXmlProperty(localName = "ToUserName")
    private String toUser;

    @JacksonXmlProperty(localName = "FromUserName")
    private String FromUser;

}
