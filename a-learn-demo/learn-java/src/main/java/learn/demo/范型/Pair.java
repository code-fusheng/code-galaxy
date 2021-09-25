package learn.demo.范型;

/**
 * @FileName: Pair
 * @Author: code-fusheng
 * @Date: 2021/6/21 11:24 上午
 * @Version: 1.0
 * @Description:
 */

public class Pair<T> {

    private T first;
    private T second;

    public Pair() {
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
