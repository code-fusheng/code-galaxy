package learn.demo.并发.原子类;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @FileName: AtomicTest3
 * @Author: code-fusheng
 * @Date: 2022/2/11 11:19 下午
 * @Version: 1.0
 * @Description:
 */

public class AtomicTest3 {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private Integer uid;
        private String uname;

    }

    public static void main(String[] args) {

        AtomicReference<User> userAtomicRef = new AtomicReference<>();

        User user = new User();
        user.setUid(1);
        user.setUname("fusheng");

        User newUser = new User();
        newUser.setUid(2);
        newUser.setUname("code");

        userAtomicRef.set(user);

        System.out.println("userAtomicRef:" + userAtomicRef.get());

        userAtomicRef.compareAndSet(user, newUser);

        System.out.println("userAtomicRef:" + userAtomicRef.get());

    }

}
