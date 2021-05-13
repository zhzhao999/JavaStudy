package top.zhzhao.thread;

/**
 * 线程的三种创建方式
 * 1.继承Thread类      单继承，不建议使用
 * 2.实现Runnable接口   推荐。避免单继承，方便同一个对象被多个线程使用
 * 3.实现Callable接口
 *
 * @author zhzhao on 2021/5/10 14:57
 */
public class ThreadDemo02 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 200; i++) {
            System.out.println("my thread test " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo02 demo02 = new ThreadDemo02();
        new Thread(demo02).start();

        for (int i = 1; i <= 200; i++) {
            Thread.sleep(1);
            System.out.println("主线程" + i);
        }
    }

}
