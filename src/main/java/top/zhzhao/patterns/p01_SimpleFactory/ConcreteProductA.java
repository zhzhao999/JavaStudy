package top.zhzhao.patterns.p01_SimpleFactory;

/**
 * 具体产品类代码
 * @Author zhzhao
 * @Date 2018/9/7 22:29
 */
public class ConcreteProductA extends Product {
    // 实现业务方法
    @Override
    public void methodDiff() {
        // 业务方法的实现
        System.out.println("产品A");
    }
}
