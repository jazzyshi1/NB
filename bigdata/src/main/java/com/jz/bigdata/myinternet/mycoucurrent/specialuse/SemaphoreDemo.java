package com.jz.bigdata.myinternet.mycoucurrent.specialuse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量（控制并发线程数）
 * Created by jazzyshi on 2018/10/12.
 */
public class SemaphoreDemo implements Runnable{

    private Semaphore semaphore;//信号量
    private int userNumber;//记录第几个用户

    public SemaphoreDemo(Semaphore semaphore,int userNumber){
        this.semaphore = semaphore;
        this.userNumber = userNumber;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("用户【"+userNumber+"】进入窗口，准备买票");
            Thread.sleep((long)Math.random()*10000);
            System.out.println("用户【"+userNumber+"】买票完成，准备离开");
            Thread.sleep((long)Math.random()*10000);
            System.out.println("用户【"+userNumber+"】离开售票窗口");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String ...args){

        //定义窗口数量
        final Semaphore semaphore = new Semaphore(2);
        //线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //模拟20个用户去买票
        for(int i=0;i<20;i++){
            //去买票
            threadPool.execute(new SemaphoreDemo(semaphore,i));
        }

        threadPool.shutdown();
    }


}
