package test.thread.specific_storage.main;

public class Main {

	public static void main(String[] args) {
		new ClientThread("a").start();
		new ClientThread("b").start();
		new ClientThread("c").start();
	}

}
