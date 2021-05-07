package top.zhzhao.java8.stream;

import org.junit.Test;
import top.zhzhao.java8.bean.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppleTest {

    private static final ArrayList<Apple> appleList = new ArrayList<>();
    static {
        appleList.add(new Apple(1,"red",500,"新疆"));
        appleList.add(new Apple(2,"red",400,"北京"));
        appleList.add(new Apple(3,"green",300,"新疆"));
        appleList.add(new Apple(4,"green",200,"北京"));
        appleList.add(new Apple(5,"green",100,"北京"));
    }

    @Test
    public void test(){
        List<Apple> redApple = appleList.stream()
                .filter(apple -> "red".equals(apple.getColor()))
                .filter(apple -> apple.getWeight() > 300)
                .filter(apple -> "北京".equals(apple.getOrigin()))
                .collect(Collectors.toList());
        System.out.println(redApple.size());

    }

    @Test
    public void test2(){
        //按颜色分组,并计算平均值
        HashMap<String, ArrayList<Apple>> endMap = new HashMap<>();
        for (Apple apple : appleList) {
            String color = apple.getColor();
            if (!endMap.containsKey(color)) {
                ArrayList<Apple> apples = new ArrayList<>();
                apples.add(apple);
                endMap.put(color,apples);
            }else {
                ArrayList<Apple> apples = endMap.get(color);
                apples.add(apple);
            }
        }

        for (Map.Entry<String, ArrayList<Apple>> entry : endMap.entrySet()) {
            String color = entry.getKey();
            ArrayList<Apple> apples = entry.getValue();
            int weight = 0;
            for (Apple apple : apples) {
                weight += apple.getWeight();
            }

            System.out.printf("颜色:%s --- 平均值:%s%n", color, weight / apples.size());
        }

        System.out.println("--------------------");


        Map<String, Double> collect = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingInt(Apple::getWeight)));
        collect.forEach((k,v)-> System.out.printf("颜色:%s --- 平均值:%s%n", k, v));

    }

    @Test
    public void test3(){

    }
}
