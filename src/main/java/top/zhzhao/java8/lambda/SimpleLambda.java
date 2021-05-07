package top.zhzhao.java8.lambda;

import org.junit.Test;
import top.zhzhao.java8.bean.UserBean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式
 * 好处:简化匿名内部类的调用
 * 依赖:函数接口
 */
public class SimpleLambda {
    @Test
    public void test() {
    }

    @Test
    public void test01() {
        //匿名内部类--举例
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        //lambda--举例
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }

    @Test
    public void test02() {
        //自定义函数式接口-无参
        MyFunctionInterface myFunctionInterface = () -> System.out.println("ADD method");
        myFunctionInterface.add();

        ((MyFunctionInterface) () -> System.out.println("ADD method")).add();
    }

    @Test
    public void test03() {
        //一个参数
        ((YiCanInterface) i -> System.out.println("--" + i)).add(1);
    }

    @Test
    public void test04() {
        //两个参数
        String add = ((YouCanInterface) (i, j) -> Integer.toString(i + j)).add(3, 4);
        System.out.println(add);
    }

    private static final ArrayList<UserBean> userBeans = new ArrayList<>();

    static {
        userBeans.add(new UserBean("张三", 16));
        userBeans.add(new UserBean("李四", 18));
        userBeans.add(new UserBean("王五", 9));
        userBeans.add(new UserBean("赵六", 22));
    }

    @Test
    public void test05() {
        //排序-正序
        userBeans.sort(new Comparator<UserBean>() {
            @Override
            public int compare(UserBean o1, UserBean o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        userBeans.forEach(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) {
                System.out.println(userBean);
            }
        });

    }

    @Test
    public void test06() {
        //排序-倒序
        userBeans.sort((u1, u2) -> u2.getAge() - u1.getAge());

//        userBeans.forEach(u -> System.out.println(u));
        userBeans.forEach(System.out::println);
    }
}
