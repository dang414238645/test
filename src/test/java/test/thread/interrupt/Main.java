package test.thread.interrupt;
/**
 * 线程的interrupt()方法不是中段线程
 * 只是给线程标识一个中段的状态，如果我们需要中段线程，可以捕获这种状态通过抛出异常的方式实现中短
 * sleep wait join 内部实现也是在不断的轮训这种状态来实现的，所以他会自动抛出异常，不同的是他们抛出异常后，线程就变为非中段状态
 * @author changpengfei
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread t=new InterruptThread();
		t.start();
		System.out.println(t.isInterrupted());
		t.interrupt();
		//这里必须加sleep()方法才能看到效果，因为main在的主线程可能执行完两次interrupt()，
		//线程InterruptThread才执行interrupted()方法，以至于isInterrupted()方法一直返回不会显示我们想要的结果
		Thread.sleep(1000);
		System.out.println(t.isInterrupted());
		t.interrupt();
		System.out.println(t.isInterrupted());
		
		Thread.sleep(1000);
		t.interrupt();
		System.out.println(t.isInterrupted());
	}

}
