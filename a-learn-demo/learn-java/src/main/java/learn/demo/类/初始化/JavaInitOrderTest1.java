package learn.demo.类.初始化;

public class JavaInitOrderTest1 {

    public static void main(String[] args) {
        new Child();
    }

}

/**
 * 父类
 */
class Feather {
    static {
        System.out.println("Feather static block --- 父类静态代码块");
    }
    {
        System.out.println("Feather common block --- 父类非静态代码块");
    }
    /**
     * 父类构造方法
     */
    public Feather() {
        System.out.println("Feather constructor --- 父类构造方法");
    }
}

/**
 * 子类
 */
class Child extends Feather {
    static {
        System.out.println("Child static block --- 子类静态代码块");
    }
    {
        System.out.println("Child common block --- 子类非静态代码块");
    }
    /**
     * 子类构造方法
     */
    public Child() {
        System.out.println("Child constructor --- 子类构造方法");
    }
}
