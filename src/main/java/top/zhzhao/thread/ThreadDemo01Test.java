package top.zhzhao.thread;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyURLToFile;

/**
 * 多线程同步下载网络图片
 * @author zhzhao on 2021/5/11 10:54
 */
public class ThreadDemo01Test extends Thread{
    private final String url;
    private final String destination;

    public ThreadDemo01Test(String url, String destination){
        this.destination = destination;
        this.url = url;
    }

    @Override
    public void run() {
        new WebLoader().download(url,destination);
    }


    public static void main(String[] args) {
        ThreadDemo01Test demo01Test = new ThreadDemo01Test("https://bkimg.cdn.bcebos.com/pic/c8ea15ce36d3d539b600533ae2cbfe50352ac75c1abf?x-bce-process=image/resize,m_lfit,w_255,h_340,limit_0/format,f_auto", "src/main/resources/1.jpg");
        demo01Test.start();
        ThreadDemo01Test demo01Test1 = new ThreadDemo01Test("https://bkimg.cdn.bcebos.com/pic/8694a4c27d1ed21b0ef4fdac7922cac451da81cb916f?x-bce-process=image/resize,m_lfit,w_255,h_340,limit_0/format,f_auto", "src/main/resources/2.jpg");
        demo01Test1.start();
        ThreadDemo01Test demo01Test2 = new ThreadDemo01Test("https://bkimg.cdn.bcebos.com/pic/0b55b319ebc4b74543a98ec617b009178a82b801bf85?x-bce-process=image/resize,m_lfit,w_255,h_340,limit_0/format,f_auto", "src/main/resources/3.jpg");
        demo01Test2.start();
    }
}

class WebLoader{

    public void download(String url, String destination){
        try {
            copyURLToFile(new URL(url) ,new File(destination));
            System.out.println(destination + "——下载完成");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("文件下载异常..");
        }
    }

}
