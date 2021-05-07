package top.zhzhao.algorithms.chapter01;

import org.junit.Test;

/**
 * 两个数的最大公约数
 *@author zhzhao
 *@version $ Id: Gcd.java,V 0.1 2018/3/9 17:41 zhzhao Exp $
 */
public class Gcd {

    /**
     * 计算两个非负整数p和q的最大公约数:
     *      若q是0,则最大公约数是p;
     *      否则,将p除以q,得到余数r,他们的公约数是q和r的最大公约数.
     * @return 最大公约数
     */
    public static int gcd(int p,int q){
        if (q == 0){
            return p;
        }
        int r = p % q;
        return gcd(q,r);
    }

    @Test
    public void test(){
        System.out.println(gcd(12, 6));
    }
}
