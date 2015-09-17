package test.book.thinkinjava.thread;

import java.util.concurrent.TimeUnit;


class NeedsCleanup{
	private final int id;

	public NeedsCleanup(int id) {
		super();
		this.id = id;
		System.out.println("NeedsCleanup "+id);
	}
	
	public void cleanup(){
		System.out.println("Cleaning up "+id);
	}
	
}


class Blocked3 implements Runnable{
	private volatile double d=0.0;

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
					NeedsCleanup n1=new NeedsCleanup(1);
					
					try{
						System.out.println("Sleeping");
						TimeUnit.SECONDS.sleep(1);
						NeedsCleanup n2=new NeedsCleanup(2);
						try{
							System.out.println("Calculating");
							for(int i=0;i<250000;i++){
								d=d+(Math.PI+Math.E)/d;
							}
							System.out.println("Finished time-consuming operation");
						}finally{
							n2.cleanup();
						}
						
					}finally{
						n1.cleanup();
					}
					
			}
		}catch (InterruptedException e){
			System.out.println("InterruptedException");
		}
		
	}
	
}

public class InterruptingIdiom {

	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new Blocked3());
		t.start();
		TimeUnit.SECONDS.sleep(2);
		t.interrupt();
	}

}
