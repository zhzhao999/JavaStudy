package top.zhzhao.thread;

import java.util.concurrent.*;

/**
 * @author zhzhao on 2021/5/10 15:32
 */
public class ThreadDemo03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 1; i <= 200; i++) {
            System.out.println("子线程" + i);
        }
        return "子线程成功跑起来了...";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i = 1; i <= 200; i++) {
            Thread.sleep(1);
            System.out.println("主线程" + i);
        }

        ThreadDemo03 demo03 = new ThreadDemo03();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(demo03);
        String s = future.get();
        System.out.println(s);
        executorService.shutdown();



    }
}
