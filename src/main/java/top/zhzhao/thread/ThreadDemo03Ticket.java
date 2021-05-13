package top.zhzhao.thread;

/**
 * 多线程卖票
 * @author zhzhao on 2021/5/12 18:34
 */
public class ThreadDemo03Ticket implements Runnable{
    private int ticketNum = 10;

    @Override
    public void run() {
        while (ticketNum > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取车票" + ticketNum--);
        }
    }


    public static void main(String[] args) {
        ThreadDemo03Ticket ticket = new ThreadDemo03Ticket();
        new Thread(ticket,"小明").start();
        new Thread(ticket,"黄牛").start();
        new Thread(ticket,"小王").start();
    }
}
