package test.book.thinkinjava.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class NIOBlocked implements Runnable{
	
	private final SocketChannel sc;

	public NIOBlocked(SocketChannel sc) {
		super();
		this.sc = sc;
	}



	@Override
	public void run() {
		
		try {
			System.out.println("Waiting for read() in  "+this);
			sc.read(ByteBuffer.allocate(1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting NIOBlocked.run() "+this);
	}
	
}




public class NIOInterruption {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool();
		ServerSocket server=new ServerSocket(8080);
		InetSocketAddress isa=new InetSocketAddress("localhost",8080);
		SocketChannel sc1=SocketChannel.open(isa);
		SocketChannel sc2=SocketChannel.open(isa);
		Future<?> f=exec.submit(new NIOBlocked(sc2));
		exec.shutdown();
		TimeUnit.SECONDS.sleep(1);
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		sc2.close();

	}

}
