package test.distributed_sys.socket.simple;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetName {
	public static String getHostName(InetAddress address) throws UnknownHostException{
		return InetAddress.getLocalHost().getHostName();
	}
	
	public static String getIp(InetAddress address){
		StringBuffer buf=new StringBuffer();
		byte[] ip=address.getAddress();
		for(int i=0;i<ip.length;i++){
			if(i>0){
				buf.append(".");
			}
			buf.append(ip[i]&0xff);;
		}
		return buf.toString();
	}
	
	public static InetAddress getInetAddress(String s) throws UnknownHostException{
		return InetAddress.getByName(s);
	}
	public static void main(String[] args) throws UnknownHostException {
//		ftqjk-d0997
//		172.17.7.162
		InetAddress host=null;
		host=InetAddress.getLocalHost();
		System.out.println(getIp(host));
		System.out.println(getHostName(host));
		System.out.println(getIp(getInetAddress("172.17.7.162")));
		System.out.println(getHostName(getInetAddress("172.17.7.162")));
	}

}
