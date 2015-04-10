package test.thread.gate;

public class Gate {
	private int counter=0;
	private String name="";
	private String address="";
	public synchronized void pass(String name, String address)  {
		this.counter++;
		this.name = name;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.address = address;
		check();
	}
	private void check() {
		if(!this.name.equals(this.address)){
			System.out.println("------BROKEN------");
		}
	}
	

}
