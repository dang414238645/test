package test.thread.specific_storage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	private static PrintWriter writer=null;
	static{
		try {
			writer=new PrintWriter(new FileWriter("log.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void println(String s){
		writer.println(s);
	}
	
	public static void close(){
		writer.println("End of log");
		writer.close();
	}
	

}
