package test.thread.gate;

public class UserThread extends Thread {
	
	private final Gate gate;
	private final String myname;
	private final String myaddress;

	
	@Override
	public void run() {
		System.out.println(this.myname+"--BEGIN--");
		while(true){
			gate.pass(myname, myaddress);
		}
	}


	public UserThread(Gate gate, String myname, String myaddress) {
		this.gate = gate;
		this.myname = myname;
		this.myaddress = myaddress;
	}

}
