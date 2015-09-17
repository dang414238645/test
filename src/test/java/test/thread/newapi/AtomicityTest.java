package test.thread.newapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable{

	private int i=0;
	public synchronized int getValue(){
		return i;
	}
	private synchronized void evenIncrement(){
		i++;
		Thread.yield();
		i++;
	}
	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	
	public static void main(String args[]){
		ExecutorService service=Executors.newCachedThreadPool();
		AtomicityTest at=new AtomicityTest();
		service.execute(at);
		while(true){
			int val=at.getValue();
			if(val%2!=0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}
	

}
