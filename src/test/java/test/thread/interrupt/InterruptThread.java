package test.thread.interrupt;

public class InterruptThread extends Thread {

	@Override
	public void run() {
		try {
			while(true){
				//interrupted()方法执行后当前线程变为非中段状态
				if(Thread.interrupted()){
//					Thread.sleep(1000);
					throw new InterruptedException("interrupted()--");
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(true){
				//当wait sleep join 一抛出InterruptedException后，线程就不是中段状态啦
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		while(true){
			//isInterrupted()执行后对线程状态不改变
			if(Thread.currentThread().isInterrupted()){
				try {
					throw new InterruptedException("isInterrupted()--");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
}
