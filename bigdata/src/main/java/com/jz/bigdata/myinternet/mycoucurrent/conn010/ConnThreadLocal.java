package com.jz.bigdata.myinternet.mycoucurrent.conn010;

/**
 * ThreadLocal:线程局部变量,是一种多线程间并发访问变量的解决方案。
 * 其完全不提供锁，使用空间换时间的手段，为每个线程提供变量的独立副本，以保障线程安全。
 * 从性能上说，不具备绝对的优势，在并发不是很高的时候，加锁的性能会更好。
 */
public class ConnThreadLocal {

	public static ThreadLocal<String> th = new ThreadLocal<String>();
	
	public void setTh(String value){
		th.set(value);
	}
	public void getTh(){
		System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("张三");
				ct.getTh();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					ct.getTh();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
	
}
