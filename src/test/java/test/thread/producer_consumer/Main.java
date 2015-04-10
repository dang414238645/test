package test.thread.producer_consumer;

public class Main {

	public static void main(String[] args) {
		Table table=new Table(3);
		new MarkerThread("MarkerThread_1", table, 31415).start();
		new MarkerThread("MarkerThread_2", table, 92653).start();
		new MarkerThread("MarkerThread_3", table, 96534).start();
		new EaterThread("EaterThread_1", table, 25648).start();
		new EaterThread("EaterThread_2", table, 84162).start();
		new EaterThread("EaterThread_3", table, 20135).start();
	}

}
