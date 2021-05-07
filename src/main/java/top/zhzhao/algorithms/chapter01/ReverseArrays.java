package top.zhzhao.algorithms.chapter01;

import org.junit.Test;

import java.util.Arrays;

/**
 * 颠倒数组
 *@author zhzhao
 *@version $ Id: ReverseArrays.java,V 0.1 2018/3/9 18:19 zhzhao Exp $
 */
public class ReverseArrays {


    private static void revers(int[] arr) {
        int length = arr.length;
        for (int i = 0;i < length/2;i++){
            int temp  = arr[i];
            arr[i] = arr[length-1-i];
            arr[length-i-1] = temp;
        }
    }

    @Test
    public void test01() {
        int[] arr = {1,2,3,4,5};
        revers(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

}
