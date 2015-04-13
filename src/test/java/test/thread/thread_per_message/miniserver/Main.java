package test.thread.thread_per_message.miniserver;

import java.io.IOException;

public class Main {
	public static void main(String args[]){
		try {
			new MiniServer(8888).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
