package app;

public interface jdk18 {

    default void look() {
        System.out.println("我是1.8之后的特性");
    }

    default boolean watch() {
        System.out.println("我在看你");
        return true;
    }

}