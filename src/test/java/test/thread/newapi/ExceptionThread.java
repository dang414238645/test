package test.thread.newapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionThread implements Runnable{

	@Override
	public void run() {
		throw new RuntimeException();
		
	}
	/**
	 * main方法里是不能捕获线程里的异常的
	 * 通过两种方式
	 * 1：Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
	 * 2：ExecutorService service1=Executors.newCachedThreadPool(new HandlerThreadFactor());
	 * @param args
	 */
	public static void main(String args[]){
		
		try{
//			Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
//			ExecutorService service=Executors.newCachedThreadPool();
//			service.execute(new ExceptionThread());
			
			
			ExecutorService service1=Executors.newCachedThreadPool(new HandlerThreadFactor());
			service1.execute(new ExceptionThread2());
		}catch(Exception e){
			System.out.println("无用的");
		}
		
	}
	
}

class ExceptionThread2  implements Runnable{

	@Override
	public void run() {
		Thread thread=Thread.currentThread();
		System.out.println("run() by"+thread);
		System.out.println("eh="+thread.getUncaughtExceptionHandler());
		throw new RuntimeException();
		
	}
	
}


class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught"+e);
	}
	
}

class HandlerThreadFactor implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this+"creating new Thread");
		Thread thread=new Thread(r);
		System.out.println("creat"+thread);
		thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh="+thread.getUncaughtExceptionHandler());
		return thread;
	}
	
}





