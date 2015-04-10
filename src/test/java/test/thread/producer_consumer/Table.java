package test.thread.producer_consumer;

import java.util.Arrays;

public class Table {
	private final String[] buffer;
	private int head;
	private int tail;
	private int count;
	public Table(int count) {
		this.buffer=new String[count];
		this.head=0;
		this.tail=0;
		this.count=0;
	}
	
	public synchronized void put(String cake) throws InterruptedException{
		while(count>=buffer.length){
			wait();
		}
		buffer[tail]=cake;
		tail=(tail+1)%buffer.length;
		count++;
		System.out.println(Thread.currentThread().getName()+" , puts "+cake+"	"+toString());
		notifyAll();
	}
	
	public synchronized String take() throws InterruptedException{
		while(count<=0){
			wait();
		}
		String cake=buffer[head];
		buffer[head]=null;
		head=(head+1)%buffer.length;//取余数
		count--;
		System.out.println(Thread.currentThread().getName()+" , take "+cake+"	"+toString());
		notifyAll();
		return cake;
	}

	@Override
	public synchronized String toString() {
		return "[head="+head+",tail="+tail+",count="+count+",buffer="+Arrays.toString(buffer)+"]";
	}
	
	
	
}
