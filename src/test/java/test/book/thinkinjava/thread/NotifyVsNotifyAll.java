package test.book.thinkinjava.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Blcocker{
	synchronized void waitingCall(){
		try {
			while(!Thread.interrupted()){
				wait();
				System.out.println(Thread.currentThread()+"	");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized void prod(){
		notify();
	}
	synchronized void prodAll(){
		notifyAll();
	}
	
}


class Task implements Runnable{
	static Blcocker blcocker=new Blcocker();
	public void run(){
		blcocker.waitingCall();
	}
}

class Task2 implements Runnable{
	static Blcocker blcocker=new Blcocker();
	public void run(){
		blcocker.waitingCall();
	}
}


public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		Timer timer=new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod=true;
			@Override
			public void run() {
				if(prod){
					System.out.println("notify() ");
					Task.blcocker.prod();
					prod=false;
					
				}else{
					System.out.println("notifyAll() ");
					Task.blcocker.prodAll();
					prod=true;
				}
				
			}
		}, 400, 400);
		
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();
		System.out.println("timer.cancel()");
		TimeUnit.SECONDS.sleep(10);
		Task2.blcocker.prodAll();
		System.out.println("shuting down ");
		exec.shutdown();
	}

}
