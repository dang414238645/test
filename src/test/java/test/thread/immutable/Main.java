package test.thread.immutable;

public class Main {
	public static void main(String args[]){
		Person person=new Person("a", "b");
		new PrintPersonThread(person).start();
		new PrintPersonThread(person).start();
		new PrintPersonThread(person).start();
	}
}
