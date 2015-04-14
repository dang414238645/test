package test.thread.worker_thread;

import java.util.Random;

public class ClientThread extends Thread {
	private final Channel channel;
	
	private static Random random=new Random();
	
	public ClientThread(String name,Channel channel) {
		super(name);
		this.channel = channel;
	}
	
	public void stopThread(){
		this.interrupt();
	}

	@Override
	public void run() {
			try {
				for(int i=0;true;i++){
					if(Thread.interrupted()){
						throw new InterruptedException();
					}
					Request request=new Request(getName(),i);
					channel.putRequest(request);
					Thread.sleep(random.nextInt(1000));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
