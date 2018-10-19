package com.jz.bigdata.myinternet.mycoucurrent.specialuse;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jazzyshi on 2018/10/13.
 */
public class CountDownLatchDemo {
    public static void main(String ...args){
        final CountDownLatch latch = new CountDownLatch(10);
        //线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int finalI = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("任务【"+ finalI +"】开始执行");
                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println("任务【"+ finalI +"】执行完毕");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            };
            threadPool.execute(r);
        }
        threadPool.shutdown();
        //主线程
        System.out.println("等待其他10个线程任务执行完毕，主线程才开开始执行任务："+Thread.currentThread().getName());
        try {
            latch.await();
            System.out.println("其他10个线程任务执行完毕，主线程开始执行任务："+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
