package com.jz.bigdata.myinternet.mycoucurrent.concurrent017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程框架Executor:为了更好的控制线程。在java.util.concurrent包中
 * 多任务执行框架
 * 重要类：Executors 扮演线程工厂的角色
 * 1.newFixedThreadPool(),该方法发回一个固定数量的线程池，
 *   若线程池空闲立即执行，若没有则会被暂缓在一个任务队列中等待有空闲的线程执行
 * 2.newSingleThreadExecuter()，创建一个线程的线程池
 *   若线程池空闲立即执行，若没有则会被暂缓在一个任务队列中等待有空闲的线程执行
 * 3.newCachedThreadPool(),可根据实际情况调整线程池的个数，
 *   每一个空闲的线程会在默认60s后自动回收
 * 4.newScheduledThreadPool()方法，该方法返回SchededExecutorService对象，可以指定线程池的大小
 *   队列是elayedQueueD，可以实现定时功能
 */

public class UseExecutors {

	public static void main(String[] args) {
		
		//ExecutorService pool = Executors.newSingleThreadExecutor()
		
		//cache fixed single
		
		
		
	}
}
