package top.zhzhao.thread;

/**
 * 线程的三种创建方式
 * 1.继承Thread类
 * 2.实现Runnable接口
 * 3.实现Callable接口
 *
 * @author zhzhao on 2021/5/10 14:57
 */
public class ThreadDemo01 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 200; i++) {
            System.out.println("my thread test " + i);
        }
    }

    public static void main(String[] args) {
        new ThreadDemo01().start();

        for (int i = 1; i <= 2000; i++) {
            System.out.println("主线程" + i);
        }


    }

}
