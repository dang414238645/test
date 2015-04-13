package test.thread.thread_per_message.miniserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Service {
	private Service(){}
	public static void service(Socket clientSocket){
		System.out.println(Thread.currentThread().getName()+":Service.service("+clientSocket+") BEGIN");
		DataOutputStream out=null;
		try {
			out=new DataOutputStream(clientSocket.getOutputStream());
			out.writeBytes("HTTP/1.0 200 OK\r\n");
			out.writeBytes("Content-type: text/html\r\n");
			out.writeBytes("\r\n");
			out.writeBytes("<html><head><title>CountDown</title></head><body>");
			out.writeBytes("<h1>CountDown start!</h1>");
			out.writeBytes("</body></html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
