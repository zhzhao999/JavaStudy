package top.zhzhao.java8.optional;

import org.junit.Test;
import top.zhzhao.java8.bean.UserBean;

import java.util.Optional;

public class OptionalDemo {

    /**
     * Optional.ofNullable  入参允许为空  常用
     * Optional.of          入参不允许为空
     */
    @Test
    public void test01() {
        String tempStr = null;
        Optional<String> optional = Optional.ofNullable(tempStr);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    /**
     * orElse
     */
    @Test
    public void test02() {
        String tempStr = null;
        String endStr = Optional.ofNullable(tempStr).orElse("zhzhao");
        System.out.println(endStr);
    }

    /**
     * filter
     */
    @Test
    public void test03() {
        String tempStr = "aaa";
        Optional<String> optional = Optional.ofNullable(tempStr).filter(a -> "aaa".equals(a));
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    /**
     * isPresent
     */
    @Test
    public void test04() {
        String tempStr = "null";
        Optional<String> optional = Optional.ofNullable(tempStr);
        optional.ifPresent(System.out::println);
    }

    /**
     * orElse       直接赋值
     * orElseGet    入参为提供者，可进行中间操作
     */
    private UserBean userBean;

    @Test
    public void test05() {
        UserBean userBean2 = Optional.ofNullable(this.userBean).orElseGet(() -> new UserBean("zhangsan", 18));
        System.out.println(userBean2);
    }

    /**
     * map 映射
     */
    @Test
    public void test06() {
        UserBean userBean = new UserBean("zhangSAN", 18);
//        String userName = Optional.ofNullable(userBean).map(user -> user.getName()).map(name -> name.toLowerCase()).orElse("没值..");
        String userName = Optional.ofNullable(userBean).map(UserBean::getName).map(String::toLowerCase).orElse("没值..");
        System.out.println(userName);
    }
}
