package test.thread.active_object.activeobject;

public class Proxy implements ActiveObject{
	
	private final SchedulerThread scheruler;
	
	private final Servant servant;
	
	
	
	public Proxy(SchedulerThread scheruler, Servant servant) {
		this.scheruler = scheruler;
		this.servant = servant;
	}

	@Override
	public Result makeString(int count, char fillchar) {
		FutureResult future=new FutureResult();
		scheruler.invoke(new MakingStringRequest(servant, future,count,fillchar));
		return future;
	}

	@Override
	public void displayString(String string) {
		scheruler.invoke(new DisplayStringRequest(servant, string));
	}

}
