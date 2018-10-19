package com.jz.bigdata.myinternet.mycoucurrent.coll013;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * ConcurrentMap接口下两个主要实现：1.ConcurrentHashMap 2..ConcurrentSkipListMap(支持并发排序功能，参考treemap)
 *
 * HashTable - > ConcurrentHashMap（把大的容器切分成16分小的hashtable，减小锁粒度，减少锁竞争
 * ,并且大多共享变量使用volatile关键字声明，目的是第一时间获取修改的内容，性能非常好）
 */
public class UseConcurrentMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		chm.putIfAbsent("k3", "vvvv");//如果存在则不添加、put是存在则替换
		System.out.println(chm.get("k2"));
		System.out.println(chm.size());
		
		for(Map.Entry<String, Object> me : chm.entrySet()){
			System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
		}
		
		
		
	}
}
