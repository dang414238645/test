package test.thread.demo1;

public class MyThread implements Runnable {

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.print("Nice");
		}
	}
	
	public static void main(String args[]) throws InterruptedException{
		Thread t=new Thread(new MyThread());
		t.start();
		//下面的写法同Thread。sleep(10000)
		t.sleep(10000);
		for(int i=0;i<100;i++){
			System.out.print("Good");
		}
		
	}

}
