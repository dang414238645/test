package test.thread.specific_storage;

public class Main {

	public static void main(String[] args) {
		System.out.println("BEGIN");
		for(int i=0;i<10;i++){
			Log.println("main:i="+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.close();
		System.out.println("END");
	}

}
