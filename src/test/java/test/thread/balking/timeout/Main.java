package test.thread.balking.timeout;

public class Main {

	public static void main(String[] args) {
		Host host=new Host(10000);
		try {
			System.out.println("execute BEGIN");
			host.execute();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
