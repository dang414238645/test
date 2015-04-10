package test.thread.gate;

public class Main {
	public static void main(String args[]){
		Gate gate=new Gate();
		new UserThread(gate, "a", "a").start();
		new UserThread(gate, "b", "b").start();
		new UserThread(gate, "ac", "ac").start();
	}
}
