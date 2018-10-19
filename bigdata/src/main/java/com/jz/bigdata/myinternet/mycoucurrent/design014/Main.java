package com.jz.bigdata.myinternet.mycoucurrent.design014;

/**
 * 并发编程设计模式-Future模式
 *
 * 类似于商品订单：提交订单，就可以在家里等待商品送货上门
 * 类似Ajax请求
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		FutureClient fc = new FutureClient();
		Data data = fc.request("请求参数");
		System.out.println("请求发送成功!");
		System.out.println("做其他的事情...");
		
		String result = data.getRequest();
		System.out.println(result);
		
	}
}
