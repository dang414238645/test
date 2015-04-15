package test.thread.specific_storage.main;


public class ClientThread extends Thread {
	
	public ClientThread(String name) {
		super(name);
	}
	
	public void run(){
			try {
				System.out.println(getName()+" 	BEGIN");
				for(int i=0;i<10;i++){
					Log.println("main:i="+i);
					Thread.sleep(1000);
				}
				System.out.println(getName()+" END");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Log.close();
			}
		
	}

	
	
	
}
