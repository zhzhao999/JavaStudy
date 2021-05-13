package top.zhzhao.thread;

/**
 * 多线程同步下载网络图片
 * @author zhzhao on 2021/5/11 10:54
 */
public class ThreadDemo02Test implements Runnable{
    private final String url;
    private final String destination;

    public ThreadDemo02Test(String url, String destination){
        this.destination = destination;
        this.url = url;
    }

    @Override
    public void run() {
        new WebLoader().download(url,destination);
    }


    public static void main(String[] args) {

        ThreadDemo02Test demo01Test1 = new ThreadDemo02Test("https://bkimg.cdn.bcebos.com/pic/c8ea15ce36d3d539b600533ae2cbfe50352ac75c1abf?x-bce-process=image/resize,m_lfit,w_255,h_340,limit_0/format,f_auto", "src/main/resources/1.jpg");
        ThreadDemo02Test demo01Test2 = new ThreadDemo02Test("https://bkimg.cdn.bcebos.com/pic/8694a4c27d1ed21b0ef4fdac7922cac451da81cb916f?x-bce-process=image/resize,m_lfit,w_255,h_340,limit_0/format,f_auto", "src/main/resources/2.jpg");
        ThreadDemo02Test demo01Test3 = new ThreadDemo02Test("https://bkimg.cdn.bcebos.com/pic/0b55b319ebc4b74543a98ec617b009178a82b801bf85?x-bce-process=image/resize,m_lfit,w_255,h_340,limit_0/format,f_auto", "src/main/resources/3.jpg");

        new Thread(demo01Test1).start();
        new Thread(demo01Test2).start();
        new Thread(demo01Test3).start();
    }
}

