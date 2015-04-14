package test.thread.worker_thread;

public class Main {

	public static void main(String[] args) {
		Channel channel=new Channel(5);
		channel.startWorkers();
		//工作的委托人（创建请求）
		ClientThread a=new ClientThread("A", channel);
		ClientThread b=new ClientThread("B", channel);
		ClientThread c=new ClientThread("C", channel);
		a.start();
		b.start();
		c.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a.stopThread();
		b.stopThread();
		c.stopThread();
		channel.stopAllWorkers();

	}

}
