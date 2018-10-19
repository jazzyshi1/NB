package com.jz.bigdata.myinternet.mycoucurrent.conn011;

/**
 * 单例模式,最常见的就是饥饿模式和懒汉模式，一个直接实例化对象，一个在调用的时候进行实例化对象
 *
 * 在多线程模式下，考虑到性能和线程安全问题，我们一般选择
 * double check instance（双重判断） 和 static inner class（静态内部类）
 */
public class Singletion {
	
	private static class InnerSingletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return InnerSingletion.single;
	}
	
}
