package test.thread.specific_storage.main;

public class Log {
	//ThreadLocal 每一个实例对象 可以存放多个线程的数据 当某个线程调用 get方法时 只去当前线程放入的value，其实其就相当与一个map，key就是线程的id，每次get时自动取得自己线程id对应的值
	private static final ThreadLocal<Tslog> tslogConllection=new ThreadLocal<Tslog>();
	
	
	
	public static void println(String s){
		getTsLog().println(s);
	}
	
	public static void close(){
		getTsLog().println("End of log");
		getTsLog().close();
	}
	
	
	private static Tslog getTsLog(){
		Tslog tslog=tslogConllection.get();
		
		if(tslog==null){
			tslog=new Tslog(Thread.currentThread().getName()+"_log.txt");
			tslogConllection.set(tslog);
		}
		
		return tslog;
	}
	
	
}
