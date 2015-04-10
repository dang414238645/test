package test.thread.guard;

import java.util.Random;

public class ServerThread extends Thread {
	private Random random;
	private RequestQueue requestQueue;
	
	

	public ServerThread(Random random, RequestQueue requestQueue,String name) {
		super(name);
		this.random = random;
		this.requestQueue = requestQueue;
	}

	@Override
	public void run() {
		try {
			for(int i=0;i<10000;i++){
				Request request=requestQueue.getRequest();
				System.out.println(Thread.currentThread().getName()+" requests "+request);
//				Thread.sleep(random.nextInt(1000));
				if(Thread.currentThread().isInterrupted()){
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.print("--------------------------");
			e.printStackTrace();
		}
	}

}
