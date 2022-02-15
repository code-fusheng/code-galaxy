package learn.demo.高级;

import com.google.common.base.Stopwatch;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @FileName: StopwatchTest
 * @Author: code-fusheng
 * @Date: 2022/1/18 11:44 下午
 * @Version: 1.0
 * @Description: 程序秒表
 */

public class StopwatchTest {

    public static void main(String[] args) {

        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(stopwatch.stop().elapsed(TimeUnit.SECONDS));

    }

}
