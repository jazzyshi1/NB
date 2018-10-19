package com.jz.bigdata.myinternet.mycoucurrent.coll012;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 *
 * 同步类容器：如古老的Vector、HashTable,其实都是由JDK的Collections.synchronized等工厂方法实现
 * ，其底层无非是用synchronized关键字对每个公用方法都进行同步，使得每次只能有一个线程访问容器的状态
 *
 * 同步类容器都是线程安全的。
 * 但是复合查询（迭代-反复访问元素，遍历完容器中所有元素）、
 * 跳转（根据指定的顺序找到当前元素的下一个元素）、
 * 以及条件运算。这些复合操作在多线程并发修改容器时，可能会出现意外的行为，
 * 最经典的是ConcurrentModificationException,原因是容器迭代的过程中，被并发的修改了内容
 *
 * @author alienware
 */
public class Tickets {

	public static void main(String[] args) {
		//初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap
		
		final Vector<String> tickets = new Vector<String>();
		
		//Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

		for(int i = 1; i<= 1000; i++){
			tickets.add("火车票"+i);
		}
		
//		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			tickets.remove(20);
//		}
		
		for(int i = 1; i <=10; i ++){
			new Thread("线程"+i){
				public void run(){
					while(true){
						if(tickets.isEmpty()) break;
						System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
					}
				}
			}.start();
		}
	}
}
