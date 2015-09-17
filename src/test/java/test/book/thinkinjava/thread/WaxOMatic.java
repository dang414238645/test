package test.book.thinkinjava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
	private boolean waxOn=false;
	public synchronized void waxed(){
		waxOn=true;
		notifyAll();
	}
	public synchronized void buffed(){
		waxOn=false;
		notifyAll();
	}
	
	public synchronized void waitforWaxing() throws InterruptedException{
		while(waxOn==false)
		{
			wait();
		}
	}
	
	public synchronized void waitforBuffing() throws InterruptedException{
		while(waxOn==true)
		{
			wait();
		}
	}
}


class WaxOn implements Runnable{

	private Car car;

	public WaxOn(Car car) {
		super();
		this.car = car;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				System.out.println("Wax On!");
				TimeUnit.SECONDS.sleep(10);
				car.waxed();
				car.waitforBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("On Exiting via Interrupt");
		}
		System.out.println("Ending Wax On task");
	}
	
}


class WaxOff implements Runnable{

	private Car car;

	public WaxOff(Car car) {
		super();
		this.car = car;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				car.waitforWaxing();
				System.out.println("Wax Off!");
				TimeUnit.SECONDS.sleep(10);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("Off Exiting via Interrupt");
		}
		System.out.println("Ending Wax off task");
	}
	
}



public class WaxOMatic {

	public static void main(String[] args) throws InterruptedException {
		Car car=new Car();
		ExecutorService exec=Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdown();
	}

}
