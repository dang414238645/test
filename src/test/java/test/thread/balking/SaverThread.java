package test.thread.balking;

import java.io.IOException;

public class SaverThread extends Thread{
	private Data data;

	
	public SaverThread(String name,Data data) {
		super(name);
		this.data = data;
	}


	@Override
	public void run() {
		try {
			//模仿自动保存
			while(true){
				data.save();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
