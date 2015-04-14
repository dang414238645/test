package test.thread.thread_per_message;

public class Helper {

	public void handle(int count,char c) {
		System.out.println("	handle("+count+","+c+") BEGIN");
		for(int i=0;i<count;i++){
			slowly();
			System.out.print(c);
		}
		System.out.println();
		System.out.println("	handle("+count+","+c+") END");
	}

	private void slowly() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}