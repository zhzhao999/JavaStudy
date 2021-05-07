package top.zhzhao.patterns.p01_SimpleFactory;

/**
 * 抽象产品类方法
 * @Author zhzhao
 * @Date 2018/9/7 22:25
 */
public abstract class Product {
    //所有产品类的公共业务方法
    public void methodSame(){
        //公共业务方法的实现
        System.out.println("公共产品");
    }

    //抽象业务方法
    public abstract void methodDiff();
}
