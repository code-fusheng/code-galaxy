package learn.demo.集合.list;

import com.sun.tools.javac.util.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: ListTest1
 * @Author: code-fusheng
 * @Date: 2021/11/17 11:24 下午
 * @Version: 1.0
 * @Description:
 */

public class ListTest1 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add(null);

        System.out.println(list);

        System.out.println(list.size());
        System.out.println(CollectionUtils.isEmpty(list));

    }

}
