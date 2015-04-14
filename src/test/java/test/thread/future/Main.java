package test.thread.future;

public class Main {

	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host=new Host();
		Data data1=host.request(10, 'a');
		Data data2=host.request(20, 'b');
		Data data3=host.request(30, 'c');
		System.out.println("main otherJob BEGIN");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("main otherJob END");
		
		System.out.println("data1="+data1.getContent());
		System.out.println("data2="+data2.getContent());
		System.out.println("data3="+data3.getContent());
		
		
		System.out.println("main END");
	}

}
