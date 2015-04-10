package test.thread.producer_consumer;

import java.util.Random;

public class MarkerThread extends Thread {
	private final Random random;
	private final Table table;
	private static int id=0;
	
	public MarkerThread(String name,Table table,long seed) {
		super(name);
		this.table=table;
		this.random=new Random(seed);
	}
	
	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(random.nextInt(1000));
				String cake="[Cake NO."+nextId()+" by "+getName()+"]";
				table.put(cake);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static synchronized int nextId(){
		return id++;
	}

}
