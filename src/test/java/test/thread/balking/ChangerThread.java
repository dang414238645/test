package test.thread.balking;

import java.io.IOException;
import java.util.Random;

public class ChangerThread extends Thread{
	private Data data;
	private Random random=new Random();
	
	public ChangerThread(String name,Data data) {
		super(name);
		this.data = data;
	}


	@Override
	public void run() {
		try {
			for(int i=0;true;i++){
				data.change("NO."+i);
				Thread.sleep(random.nextInt(1000));
				//模仿手动保存
				data.save();
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
