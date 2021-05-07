package top.zhzhao.java8.methodRef;

import org.junit.Test;
import top.zhzhao.java8.bean.UserBean;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用:
 * 方法引用是一个更加紧凑，易读的Lambda表达式，注意方法引用是一个Lambda表达式，其中方法引用的操作符是双冒号"::"
 * <p>
 * 写法:(两个方法的入参和出参一致)
 * 1.对象::实例方法
 * 2.类::静态方法
 * 3.类::实例方法
 * 4.类::new         返回类型是对象
 */
public class MethodReferenceDemo {
    @Test
    public void test01() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };
        consumer.accept("hello");

        Consumer<String> consumer2 = a -> System.out.println(a);
        consumer2.accept("world");
        // 对象::实例方法
        Consumer<String> consumer3 = System.out::println;
        consumer3.accept("java");
    }

    @Test
    public void test02() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        //类::静态方法
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(1, 2));
    }

    @Test
    public void test03() {
        Function<UserBean, String> function = a -> a.getName();
        //类::实例方法
        Function<UserBean, String> function2 = UserBean::getName;
        System.out.println(function2.apply(new UserBean("xiaoming", 16)));
    }

    @Test
    public void test04() {
        Supplier<UserBean> supplier = ()->new UserBean("张三",16);
        UserBean userBean = supplier.get();
        System.out.println(userBean);
        //类::实例方法
        Supplier<UserBean> supplier2 = UserBean::new;
        UserBean userBean1 = supplier2.get();
    }
}
