package com.jz.bigdata.myinternet.mycoucurrent.specialuse;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 业务需求3：两个线程，在同一个点（阻塞点）交换数据
   绑架案一手交钱一手交货
   Exchanger 两个线程交互数据
 * Created by jazzyshi on 2018/10/12.
 */
public class ExchangeDemo {
    public static void main(String[] args){
        //交换器，交换String类型数据
        Exchanger<String> ec = new Exchanger<>();
        //线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //线程1数据
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    String data = ec.exchange("1000万");
                    System.out.println("r1用1000万交换回："+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //线程1数据
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    String data = ec.exchange("美女");
                    System.out.println("r2用美女交换回："+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadPool.execute(r1);
        threadPool.execute(r2);

        threadPool.shutdown();
    }
}
