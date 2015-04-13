package test.thread.read_write_lock;

public class ReadWriteLock {
	
	private int readingReaders=0;//正在读取的线程数
	private int waitingWriters=0;//正在等待写入的线程数
	private int writingWriters=0;//实际正在写入的线程数
	private boolean preferWriter=true;//写入优先的话值为true
	

	public synchronized void readLock() throws InterruptedException{
		//写入优先如下面的控制，当等待的写入线程大于零，会优先wait掉读取的线程
		while(writingWriters>0||(preferWriter&&waitingWriters>0)){
			wait();
		}
		readingReaders++;
	}
	

	public synchronized void readUnlock() {
		readingReaders--;
//		preferWriter=true;
		notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		waitingWriters++;
		try {
			while(readingReaders>0||writingWriters>0){
				wait();
			}
		} finally{
			waitingWriters--;
		}
		writingWriters++;
	}

	public synchronized void writeUnlock() {
		writingWriters--;
//		preferWriter=false;
		notifyAll();
	}

}
