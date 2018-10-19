package com.jz.bigdata.myinternet.mycoucurrent.coll013;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * copy-on-write 是一种用于程序设计的优化策略
 * JDK的COW容器有两种CopyOnWriteArrayList、CopyOnWriteArraySet
 * COW容器即写时复制的容器。通俗的将添加时先拷贝，复制一个新的容器，新的容器进行添加元素，添加完然后引用指向新容器
 * 好处：可以并发的读，而不需要加锁，应为当前容器不会添加任何元素。添加元素的操作在拷贝的容器上执行
 * 读写分离的思想
 */

public class UseCopyOnWrite {

	public static void main(String[] args) {
		
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();
		
		
	}
}
