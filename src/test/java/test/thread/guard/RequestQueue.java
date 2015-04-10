package test.thread.guard;

import java.util.LinkedList;

public class RequestQueue {
	private final LinkedList<Request> queue=new LinkedList<Request>();
	
	public synchronized Request getRequest() throws InterruptedException {
		//通过while来实现警戒条件，这里的等待，是等待对象锁，等待后会在当前实例的wait set中
		//多线程版的if
		while(queue.size()<=0){
			wait();//sleep不会丢掉对象锁；wait会丢掉对象锁
		}
		return queue.removeFirst();
	}

	public synchronized void setRequest(Request request) {
		queue.addLast(request);
		//不会立即丢掉对象锁，会等待当前方法执行完后才丢掉对象锁，不过其他执行后其他线程已移除当前对象的wait set
		notifyAll();
	}
	
}
