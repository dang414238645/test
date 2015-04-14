package test.thread.two_phase_termination;

public class Main {

	public static void main(String[] args) {
		System.out.println("main:BEGIN");
		
		try {
			CountupThread t=new CountupThread();
			t.start();
			Thread.sleep(10000);
			System.out.println("main:shutdownRequest");
			t.shutdownRequest();
			
			System.out.println("main:join");
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("main:END");
		
		
		
		
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				System.out.println("***");
				System.out.println(Thread.currentThread().getName()+":Shutdown Hook!");
				System.out.println("***");
			}
		});
		
		
		System.exit(0);
	}

}
