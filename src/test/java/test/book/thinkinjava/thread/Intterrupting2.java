package test.book.thinkinjava.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class BlockedMutex{
	private Lock lock=new ReentrantLock();

	public BlockedMutex() {
		super();
		lock.lock();
	}
	
	public void f(){
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interrupted from lock acquired in f()");
		}
	}
	
}

class Blocked2 implements Runnable{
	BlockedMutex blocked=new BlockedMutex();
	@Override
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of  blocked call");
	}
	
}



public class Intterrupting2 {
	public static void main(String args[]) throws InterruptedException{
		Thread t=new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
		
	}
}
