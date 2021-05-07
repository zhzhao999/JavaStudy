package top.zhzhao.java8.lambda;

/**
 * 函数接口:
 *  1.接口中只允许有一个抽象方法
 *  2.可以定义Object类中的方法 toString(),equals()等.
 *  3.可以使用默认或者静态方法
 *  4.一般都会用@FunctionalInterface标识
 *      用注解标识的一定是函数接口,没有标识的只要符合1.2.3也识函数接口
 */
@FunctionalInterface
public interface MyFunctionInterface {

    void add();

    default void init(){
        System.out.println("init method");
    }

    @Override
    String toString();

    @Override
    boolean equals(Object obj);
}

