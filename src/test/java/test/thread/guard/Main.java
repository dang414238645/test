package test.thread.guard;

import java.util.Random;
/**
 * client 生成
 * server 消费
 * @author changpengfei
 *
 */
public class Main {

	public static void main(String[] args) {
		RequestQueue requestQueue=new RequestQueue();
		Thread client=new ClientThread(new Random(100000), requestQueue, "client");
		Thread server=new ServerThread(new Random(200000), requestQueue, "server");
		client.start();
		server.start();
		
		
		
		//下面没有把线程中断 是因为代码中的异常被我们捕获啦，把异常抛出，并跳出循环，就可以正常的停止线程啦
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		client.interrupt();
		server.interrupt();
		

	}

}
