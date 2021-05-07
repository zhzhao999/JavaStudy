package top.zhzhao.java8.stream;

import org.junit.Test;
import top.zhzhao.java8.bean.UserBean;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream特点：
 * 1.自身不存储数据
 * 2.不改变源对象。会返回一个持有结果的新stream，旧的stream无法再次使用。
 * 3.操作是延时的。需要结果时才执行。
 * 4.数据是一条所有操作(中间操作和终止操作)执行完再执行下一条
 *
 * 使用步骤:
 * 1.创建stream
 * 2.中间操作
 *      一步或多步，将初始stream一步步变成其他新的stream
 * 3.终止操作
 *      产生结果。强制之前的延时操作立即执行，并销毁stream。
 *
 * 创建方式：
 *  Collection对象创建stream流的两种方式
 *      1. 串行流.stream() 单线程
 *      2. 并行流.parallelStream() 多线程 效率高
 *  Arrays类的stream()
 *  Stream接口的of(),iterate(),generate()
 *  其他Stream接口(IntStream,LongStream,DoubleStream)的of(),range(),rangeClosed()方法
 *
 * 常见的中间操作和终止操作：
 *  中间操作：
 *      filter limit skip distinct sorted map(映射)
 *  终止操作：
 *      forEach min max count
 *      reduce(规约) collect(收集)
 */
public class StreamDemo {
    private static final ArrayList<UserBean> userList = new ArrayList<>();

    static {
        userList.add(new UserBean("张三", 18));
        userList.add(new UserBean("李四", 16));
        userList.add(new UserBean("王五", 19));
        userList.add(new UserBean("赵六", 15));
//        userList.add(new UserBean("赵六", 17));
//        userList.add(new UserBean("赵六", 15));
    }

    /**
     * Stream创建
     */
    @Test
    public void test00() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("张三");
        arrayList.add("李四");
        arrayList.add("王五");
//        arrayList.stream().forEach(System.out::println);
        arrayList.forEach(System.out::println);

        String[] arr = {"111","222","333"};
        Arrays.stream(arr).forEach(System.out::println);

        Stream.of(1,2,3,4,5).forEach(System.out::println);
        //迭代流
        System.out.println("-------迭代流------");
        Stream.iterate(0, x -> x + 2).limit(5).forEach(System.out::println);
        //生成流
        System.out.println("-------生成流------");
        Stream.generate(()->new Random().nextInt(20)).limit(5).forEach(System.out::println);
        //其他流
        System.out.println("-------其他流------");
        IntStream.of(1,2,3,4,5).forEach(System.out::println);
        IntStream.range(0,3).forEach(System.out::println); //不包含边界
        IntStream.rangeClosed(0,3).forEach(System.out::println); //包含边界
    }

    /**
     * List转Set
     */
    @Test
    public void test01() {
        HashSet<UserBean> collect = new HashSet<>(userList);
        collect.forEach(System.out::println);
        System.out.println("----");
        Set<UserBean> collect1 = userList.parallelStream().collect(Collectors.toSet());
        collect1.forEach(System.out::println);
    }

    /**
     * List转Map key为name value为User对象
     */
    @Test
    public void test02() {
        //先去重
        HashSet<UserBean> userSet = new HashSet<>(userList);

        Map<String, UserBean> collect = userSet.stream().collect(Collectors.toMap(new Function<UserBean, String>() {
            @Override
            public String apply(UserBean userBean) {
                return userBean.getName();
            }
        }, new Function<UserBean, UserBean>() {

            @Override
            public UserBean apply(UserBean userBean) {
                return userBean;
            }
        }));

        collect.forEach((k, v) -> System.out.println("key:" + k + ", value:" + v));

        System.out.println("--------------------");

        //lambda表达式
        Map<String, Object> collect2 = userSet.stream().collect(Collectors.toMap(UserBean::getName, userBean -> userBean));

        collect2.forEach((k, v) -> System.out.println("key:" + k + ", value:" + v));
    }

    /**
     * 求和
     */
    @Test
    public void test03() {
        //数字求和
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> reduce = integerStream.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        System.out.println(reduce.get());

        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5);
//        Optional<Integer> reduce2 = integerStream2.reduce((i1,i2)-> i1+i2);
        Optional<Integer> reduce2 = integerStream2.reduce(Integer::sum);
        System.out.println(reduce2.get());

        //对象年龄求和
        Optional<UserBean> sum2 = userList.stream().reduce((userBean, userBean2) -> {
            UserBean sum = new UserBean("sum", userBean.getAge() + userBean2.getAge());
            return sum;
        });

        System.out.println(sum2.get());
    }

    /**
     * 最大,最小
     */
    @Test
    public void test04() {
        Optional<UserBean> max = userList.stream().max(new Comparator<UserBean>() {
            @Override
            public int compare(UserBean o1, UserBean o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(max.get());

//        Optional<UserBean> min = userList.stream().min((o1, o2) -> o1.getAge() - o2.getAge());
        Optional<UserBean> min = userList.stream().min(Comparator.comparingInt(UserBean::getAge));
        System.out.println(min.get());
    }

    /**
     * anyMatch allMatch
     */
    @Test
    public void test05() {
        boolean b = userList.stream().anyMatch(new Predicate<UserBean>() {
            @Override
            public boolean test(UserBean userBean) {
                return "张三".equals(userBean.getName());
            }
        });
        System.out.println(b);

        boolean b2 = userList.stream().allMatch(userBean -> "张三".equals(userBean.getName()));
        System.out.println(b2);
    }

    /**
     * 过滤器
     */
    @Test
    public void test06() {
        userList.stream()
                .filter(userBean -> "赵六".equals(userBean.getName()) && userBean.getAge() > 16)
                .forEach(System.out::println);
    }

    /**
     * skip limit
     */
    @Test
    public void test07() {
        userList.stream()
                .skip(1)
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void test08() {
        userList.stream()
//                .sorted((u1,u2)->Integer.compare(u1.getAge(),u2.getAge()))
                .sorted(Comparator.comparingInt(UserBean::getAge))
                .forEach(System.out::println);
    }

    /**
     * 串行 并行流
     */
    @Test
    public void test09() {
        ArrayList<String> idList = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            idList.add(UUID.randomUUID().toString());
        }
        System.out.println(idList.size());

        long start = System.currentTimeMillis();
        //串行用时 4670  并行用时2499
        idList.parallelStream().sorted().count();
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end-start));
    }

    /**
     * reduce 规约
     *  获取年龄和
     */
    @Test
    public void test10() {
        Optional<Integer> reduce = userList.stream().map(UserBean::getAge).reduce(Integer::sum);
        reduce.ifPresent(System.out::println);
    }

    /**
     * collect 收集
     *  获取名字 并转换成list
     */
    @Test
    public void test11() {
        userList.stream().map(UserBean::getName).collect(Collectors.toList()).forEach(System.out::println);
    }
}
