package test.distributed_sys.socket.simple;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
	public static final int REMOTE_PORT=5000;
	public static void main(String args[]){
		Socket cl=null,cl2=null;
		BufferedReader is=null;
		DataOutputStream os=null;
		BufferedReader sdin=new BufferedReader(new InputStreamReader(System.in));
		String userinput=null;
		String output=null;
		
		
		try {
			cl=new Socket("ftqjk-d0997.douyole.com",REMOTE_PORT);
			is=new BufferedReader(new InputStreamReader(cl.getInputStream()));
			os=new DataOutputStream(cl.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			System.out.println("please input a keyword:");
			userinput=sdin.readLine();
			os.writeBytes(userinput+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error writing to server."+e);
		}
		
		
		try {
			output=is.readLine();
			System.out.println("get from server:"+output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			is.close();
			os.close();
			cl.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error writing..."+e);
		}
		
		
	}
}
