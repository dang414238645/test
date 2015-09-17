package test.thread.newapi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenGenerator extends IntGenerator{
	private int currentEvenValue=0;
	
	private Lock lock=new ReentrantLock();
	
	@Override
	public synchronized int next() {
//		try{
			lock.lock();
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
//		}finally{
//			lock.unlock();
//		}
		return currentEvenValue;
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
