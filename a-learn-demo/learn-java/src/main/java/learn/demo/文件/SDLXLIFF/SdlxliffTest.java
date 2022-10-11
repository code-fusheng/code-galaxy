package learn.demo.文件.SDLXLIFF;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: SdlxliffTest
 * @Author: code-fusheng
 * @Date: 2022/3/6 17:33
 * @Version: 1.0
 * @Description: Sdlxliff 文件解析
 */

public class SdlxliffTest {

    public static void main(String[] args) throws IOException {

        Map<Integer, String> sourceMap = new LinkedHashMap<>();
        Map<Integer, String> targetMap = new LinkedHashMap<>();

        AtomicInteger sourceCount = new AtomicInteger(0);
        AtomicInteger targetCount = new AtomicInteger(0);

        // String filePath = "F://专业源码/code-galaxy/git/code-galaxy/a-learn-demo/learn-java/file/翻译任务.docx.sdlxliff";
        String filePath = "/Users/zhanghao/code-galaxy/code-galaxy/a-learn-demo/learn-java/file/翻译任务.docx.sdlxliff";

        String onlineUrl = "https://code-business.oss-cn-hangzhou.aliyuncs.com/20220313011301/翻译任务.docx.sdlxliff";

        String s = new String(Files.readAllBytes(Paths.get(filePath)), Charset.defaultCharset());
        System.out.println(s);

        URL url = new URL(onlineUrl);

        // 1、读取 XML 文档
        // 读写XML文档主要依赖于org.dom4j.io包，有DOMReader和SAXReader两种方式。因为利用了相同的接口，它们的调用方式是一样的。

        Document document = null;

        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(url);
            System.out.println(document);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2、获取根节点
        // 根节点是 XML 分析的开始，任何 xml 分析的工作都需要从根开始

        Element rootElement = document.getRootElement();

        // 3、遍历子节点与对应的值

        Iterator<Element> attributeIterator = rootElement.elementIterator();

        while (attributeIterator.hasNext()) {
            // 3.1、遍历属性的键值
            Element attributeElement = attributeIterator.next();
            List<Attribute> attributes = attributeElement.attributes();
            System.out.println("======遍历属性的键值======");

            for (Attribute attribute : attributes) {
                System.out.println("name: " + attribute.getName() + "  value: " + attribute.getValue());
            }

            // 3.2、遍历属性下的子节点
            Iterator<Element> nodeIterator = attributeElement.elementIterator();



            while (nodeIterator.hasNext()) {
                Element nodeElement = nodeIterator.next();
                System.out.println("======遍历一级节点的键值======");
                // System.out.println("name: " + nodeElement.getName() + "  value: " + nodeElement.getStringValue());
                if ("body".equals(nodeElement.getName())) {
                    Iterator<Element> towLevelNodeIterator = nodeElement.elementIterator();
                    while (towLevelNodeIterator.hasNext()) {
                        Element towLevelElement = towLevelNodeIterator.next();
                        System.out.println("======遍历二级节点的键值======");
                        // System.out.println("name: " + towLevelElement.getName() + "  value: " + towLevelElement.getStringValue());
                        if ("group".equals(towLevelElement.getName())) {
                            Iterator<Element> thirdLevelIterator = towLevelElement.elementIterator();
                            while (thirdLevelIterator.hasNext()) {
                                Element thirdLevelElement = thirdLevelIterator.next();
                                if ("trans-unit".equals(thirdLevelElement.getName())) {
                                    Iterator<Element> last = thirdLevelElement.elementIterator();
                                    while (last.hasNext()) {
                                        Element next = last.next();
                                        if ("seg-source".equals(next.getName())) {
                                            Iterator<Element> elementIterator = next.elementIterator();
                                            while (elementIterator.hasNext()) {
                                                Element mrk = elementIterator.next();
                                                System.out.println("name: " + mrk.getName() + " value: " + mrk.getStringValue());
                                                sourceMap.put(sourceCount.incrementAndGet() , mrk.getStringValue());
                                            }
                                        } else if ("target".equals(next.getName())) {
                                            Iterator<Element> elementIterator = next.elementIterator();
                                            while (elementIterator.hasNext()) {
                                                Element mrk = elementIterator.next();
                                                System.out.println("name: " + mrk.getName() + " value: " + mrk.getStringValue());
                                                targetMap.put(targetCount.incrementAndGet() , mrk.getStringValue());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }

        sourceMap.entrySet().forEach(item -> {
            System.out.println(item.getKey() + " --- " + item.getValue());
        });

        targetMap.entrySet().forEach(item -> {
            System.out.println(item.getKey() + " --- " + item.getValue());
        });



    }

}
