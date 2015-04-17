package test.distributed_sys.socket.simple;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static final int TESTPORT=5000;
	public static void main(String args[]){
		ServerSocket checkServer=null;
//		BufferedReader is=null;
//		DataOutputStream os=null;
//		Socket clientSocket=null;
		
		
		try {
			checkServer=new ServerSocket(TESTPORT);
			System.out.println("SimpleServer started...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			while(true){
				final Socket clientSocket=checkServer.accept();
				
				new Thread(){
					public void run(){
						try {
							BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							DataOutputStream os=new DataOutputStream(clientSocket.getOutputStream());
							String line=null;
							try {
								line=is.readLine();
								System.out.println("we received:"+line);
								if(line.compareTo("Greetings")==0){
									os.writeBytes("...and salutations...");
								}else{
									os.writeBytes("you don't speak my protocol!");
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							
							try {
								os.close();
								is.close();
								clientSocket.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					}
				}.start();
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
