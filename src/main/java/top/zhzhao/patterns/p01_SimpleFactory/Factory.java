package top.zhzhao.patterns.p01_SimpleFactory;

/**
 * 工厂类
 * @author zhzhao
 * @version 2018/9/7 22:33
 */
public class Factory {
    public static  Product getProduct(String arg){
        Product product = null;
        if ("A".equalsIgnoreCase(arg)){
            product = new ConcreteProductA();
        }else if ("B".equalsIgnoreCase(arg)){
            product = new ConcreteProductB();
        }else {
            throw new RuntimeException("未查询到所需产品，请检查参数");
        }
        return product;
    }
}
