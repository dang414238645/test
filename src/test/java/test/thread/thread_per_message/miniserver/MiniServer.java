package test.thread.thread_per_message.miniserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer {
	private final int portnumber;

	public MiniServer(int portnumber) {
		super();
		this.portnumber = portnumber;
	}
	
	public void execute() throws IOException{
		ServerSocket serverSocket=new ServerSocket(portnumber);
		System.out.println("Listening on "+serverSocket);
		
		try{
			while(true){
				System.out.println("Accepting……");
				final Socket socketClient=serverSocket.accept();
				new Thread(){
					public void run(){
						System.out.println("Conected to "+socketClient);
						Service.service(socketClient);
					}
				}.start();
				
			}
		}finally{
			serverSocket.close();
		}
	}

}
