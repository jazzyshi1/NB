package com.jz.bigdata.myinternet.mycoucurrent.coll013;

import java.util.concurrent.PriorityBlockingQueue;


/**
 * 在并发队列上JDK提供两套实现。
 * 1.ConcurrentLinkedQueue为代表的高性能队列，无阻塞，值不允许null
 * 2.BlockingQueue接口为代表的阻塞队列，他的实现队列（
 *       a.ArrayBlockingQueue
 *          基于数组的阻塞队列实现，内部实现读写分离，意味着生产着和消费者不能完全并行
 *          长度需要定义,可以指定先进先出后者后进先出，也叫有界队列
 *       b.LinkedBlockingQueue
 *       	基于链表实现的阻塞队列,能够高效的处理并发，是应为内部实现了分离锁（读写分离两个锁），
 *          从而实现生产者和消费者操作的完全并行运行。他是一个无界队列
 *       c.SynchronousQueue
 *			一个没有缓冲的队列，生产者产生的数据直接会被消费者获取并消费
 *       d.DelayQueue
 *         带有延迟时间的queue，其中的元素只有当其指定的延迟时间到了，才能够从队列中获取该队列。
 *         DelayQueue的元素必须实现Delayed接口，无界队列
 *         应用场景很多：对缓存超时的数据进行移除、任务超时处理、空闲连接的关闭等
 *       e.PriorityBlocingQueue
 *       	基于优先级的的阻塞队列（优先级通过构造函数传入的Compator对象来决定，
 *       	也就是说传人的对象必须实现Comparable接口），内部控制线程同步的锁采用公平锁，无界队列
 *
 *  1，2 都继承Queue
 */
public class UseQueue {

	public static void main(String[] args) throws Exception {
		
		//高性能无阻塞无界队列：ConcurrentLinkedQueue
       /**
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");

		System.out.println(q.poll());	//a 从头部取出元素，并从队列里删除
		System.out.println(q.size());	//4 从头部取出元素，不删除元素
		System.out.println(q.peek());	//b
		System.out.println(q.size());	//4
      **/
		
		/**有界阻塞队列
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		array.add("f");
		//System.out.println(array.offer("a", 3, TimeUnit.SECONDS));
		**/
		
		
     /**
		//无界阻塞队列
		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.add("f");
		System.out.println(q.size());
		
		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}

//		List<String> list = new ArrayList<String>();
//		System.out.println(q.drainTo(list, 3));
//		System.out.println(list.size());
//		for (String string : list) {
//			System.out.println(string);
//		}
	  **/

/**
		final SynchronousQueue<String> q = new SynchronousQueue<String>();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(q.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				q.add("asdasd");
			}
		});
		t2.start();
**/

		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();

		Task t1 = new Task();
		t1.setId(3);
		t1.setName("id为3");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("id为4");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("id为1");

		//return this.id > task.id ? 1 : 0;
		q.add(t1);	//3
		q.add(t2);	//4
		q.add(t3);  //1

		// 1 3 4
		System.out.println("容器：" + q);
		System.out.println(q.take().getId());
		System.out.println("容器：" + q);
//		System.out.println(q.take().getId());
//		System.out.println(q.take().getId());

	}

}
