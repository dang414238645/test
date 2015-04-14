package test.thread.future.content;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.URL;

public class SyncContentImpl implements Content {
	private byte[] contentbytes;
	
	public SyncContentImpl(final String urlstr) {
		new Thread(){
			public void run(){
				System.out.println(Thread.currentThread().getName()+" Getting "+urlstr);
				try {
					URL url=new URL(urlstr);
					DataInputStream in=new DataInputStream(url.openStream());
					byte[]buffer=new byte[1];
					int index=0;
					
					try{
						while(true){
							int c=in.readUnsignedByte();
							if(buffer.length<=index){
								byte[] largerbuffer=new byte[buffer.length*2];
								System.arraycopy(buffer, 0, largerbuffer, 0, index);
								buffer=largerbuffer;
							}
							buffer[index++]=(byte)c;
						}
					}catch(EOFException e){}finally{
						in.close();
					}
					
					
					setBytes(buffer,index);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		
		
		
	}

	@Override
	public synchronized byte[] getBytes() {
		
		while(contentbytes==null){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return contentbytes;
	}
	
	public synchronized void setBytes(byte[] buffer,int index){
		contentbytes=new byte[index];
		System.arraycopy(buffer, 0, contentbytes, 0, index);
		notifyAll();
	}

}
