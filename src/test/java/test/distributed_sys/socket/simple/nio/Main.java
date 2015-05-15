package test.distributed_sys.socket.simple.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
	
	public static void test1() throws IOException{
		//获取文件
		FileChannel fc=new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/app-servlet.xml").getChannel();
		//声明缓存区空间
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		
		while(fc.read(buffer)!=-1){
			//指针 当前position设置为limit  position设置为0
			buffer.flip();
			String str=new String(buffer.array(),0,buffer.limit());
			System.out.println(str);
		}
		fc.close();
	}
	
	
	public static void test2() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile(System.getProperty("user.dir")+"/src/main/resources/app-servlet.xml", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(1024);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

		System.out.println("Read " + bytesRead);
		buf.flip();

		while(buf.hasRemaining()){
		System.out.print((char) buf.get());
		}

		buf.clear();
		bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
	

	public static void main(String[] args) throws IOException {
		test2();
	}

}
