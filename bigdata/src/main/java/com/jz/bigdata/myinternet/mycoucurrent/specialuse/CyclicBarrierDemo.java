package com.jz.bigdata.myinternet.mycoucurrent.specialuse;

import com.sun.jmx.snmp.tasks.ThreadService;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier可循环障碍物
 * 业务需求2：吃饭，各自从家里到聚餐地点，全部到齐之后，才开始一起吃东西（同步点）
   加入人没到齐，到的人只能等到那里（阻塞），直到所有人都到齐后才开始 吃饭
   吃完饭之后，到步步高广场结合，一起打游戏
   多线程合并结果
 * Created by jazzyshi on 2018/10/12.
 */
public class CyclicBarrierDemo{

    public static void main(String ...args){
        //10人聚餐
        //final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("员工全部结合完毕，吃饭前拍个照");
            }
        });
        //10人吃完饭打游戏
        final CyclicBarrier cyclicBarrierGame = new CyclicBarrier(10);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            final int finalI = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        //模拟每个人来的时间不一样
                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println("员工【"+ finalI +"】到达聚餐地点，当前已有"+(cyclicBarrier.getNumberWaiting()+1)+"人等待");
                        //阻塞
                        cyclicBarrier.await();
                        System.out.println("员工【"+ finalI +"】开始吃饭");
                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println("员工【"+ finalI +"】吃完饭");

                        //阻塞
                        cyclicBarrierGame.await();
                        System.out.println("员工【"+ finalI +"】开始打游戏");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };

            threadPool.execute(r);
        }
        threadPool.shutdown();

    }
}
