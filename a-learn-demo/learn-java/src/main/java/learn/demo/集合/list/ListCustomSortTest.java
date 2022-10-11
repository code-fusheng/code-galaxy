package learn.demo.集合.list;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @FileName: ListCustomSortTest
 * @Author: code-fusheng
 * @Date: 2022/3/21 11:17
 * @Version: 1.0
 * @Description:
 */

public class ListCustomSortTest {

    public static void main(String[] args) {

        List<TradeMark> tradeMarkList = new LinkedList<>();
        tradeMarkList.add(new TradeMark(1, 1, 0));
        tradeMarkList.add(new TradeMark(2, 0, 1));
        tradeMarkList.add(new TradeMark(3, 0, 0));
        tradeMarkList.add(new TradeMark(4, 1, 1));

        tradeMarkList.add(new TradeMark(5, 1, 1));
        tradeMarkList.add(new TradeMark(6, 0, 0));
        tradeMarkList.add(new TradeMark(7, 0, 1));

        Collections.sort(tradeMarkList);

        tradeMarkList.forEach(System.out::println);

    }

}

@Data
@AllArgsConstructor
class TradeMark implements Comparable<TradeMark> {

    private Integer id;

    private Integer isCuration;

    private Integer isTotallySatisfied;

    @Override
    public int compareTo(TradeMark tradeMark) {
        if (tradeMark.getIsCuration() > isCuration) {
            return 1;
        } else if (Objects.equals(tradeMark.getIsCuration(), isCuration)){
            if (tradeMark.getIsTotallySatisfied() > isTotallySatisfied) {
                return 1;
            } else if (tradeMark.getIsTotallySatisfied() < isTotallySatisfied) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }



}
