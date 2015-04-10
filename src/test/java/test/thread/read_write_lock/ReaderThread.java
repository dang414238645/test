package test.thread.read_write_lock;

public class ReaderThread extends Thread {
	
	private final Data data;
	
	public ReaderThread(Data data) {
		super();
		this.data = data;
	}

	@Override
	public void run() {
		
		try{
			while(true){
				char[] readbuf=data.read();
				System.out.println(Thread.currentThread().getName()+" reads "+String.valueOf(readbuf));
			}
		}catch ( InterruptedException e){
			
		}
		
	}

}
