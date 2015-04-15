package test.thread.specific_storage.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Tslog {
	private PrintWriter writer=null;
	
	
	
	public Tslog(String filename) {
		try {
			this.writer = new PrintWriter(new FileWriter(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void println(String s){
		writer.println(s);
	}
	
	public void close(){
		writer.close();
	}
	
}
