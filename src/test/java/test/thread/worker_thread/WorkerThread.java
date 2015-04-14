package test.thread.worker_thread;

public class WorkerThread extends Thread {
	
	private final Channel channel;
	

	public WorkerThread(String name,Channel channel) {
		super(name);
		this.channel = channel;
	}


	@Override
	public void run() {
		try {
			while(true){
				if(Thread.interrupted()){
						throw new InterruptedException();
				}
				Request request=channel.takeRequest();
				request.execute();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
