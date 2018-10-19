package com.jz.bigdata.myinternet.mycoucurrent.sync007;

/**
 * 加volatile让变量在多个线程间可见性，只有可见性没有原子性
 * 线程工作时会在jvm中开辟一个新的内存空间，创建主内存对象的引用
 * 加volatile让主内存变量改变时通知线程读取主内存中的变量
 * 性能比synchronized性能强，但是无法代替其同步功能
 *
 */
public class RunThread extends Thread{

	private volatile boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		while(isRunning == true){
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
	}
	
	
}
